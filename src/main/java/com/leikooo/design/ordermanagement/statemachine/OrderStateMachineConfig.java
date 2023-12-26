package com.leikooo.design.ordermanagement.statemachine;

import com.leikooo.design.ordermanagement.listener.OrderStateListener;
import com.leikooo.design.ordermanagement.state.OrderState;
import com.leikooo.design.ordermanagement.state.OrderStateChangeAction;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.data.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;

import java.util.EnumSet;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * 订单状态机配置
 */
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderStateChangeAction> {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderStateChangeAction> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.ORDER_WAIT_PAY)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderStateChangeAction> transitions) throws Exception {
        transitions
                .withExternal()
                // 从 OrderState.ORDER_WAIT_PAY 状态转化成 OrderState.ORDER_WAIT_SEND ，需要 OrderStateChangeAction.PAY_ORDER
                .source(OrderState.ORDER_WAIT_PAY)
                .target(OrderState.ORDER_WAIT_SEND)
                .event(OrderStateChangeAction.PAY_ORDER)
                .and()
                // 从 OrderState.ORDER_WAIT_SEND 状态转化成 OrderState.ORDER_WAIT_RECEIVE ，需要 SEND_ORDER
                .withExternal().source(OrderState.ORDER_WAIT_SEND)
                .target(OrderState.ORDER_WAIT_RECEIVE)
                .event(OrderStateChangeAction.SEND_ORDER)
                .and()
                .withExternal().source(OrderState.ORDER_WAIT_RECEIVE)
                .target(OrderState.ORDER_FINISH)
                .event(OrderStateChangeAction.RECEIVE_ORDER);
    }

    @Bean(name = "stateMachineRedisPersist")
    public RedisStateMachinePersister<OrderState, OrderStateChangeAction> getRedisPersister() {
        RedisStateMachineContextRepository<OrderState, OrderStateChangeAction> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        RepositoryStateMachinePersist<OrderState, OrderStateChangeAction> p = new RepositoryStateMachinePersist<>(repository);
        // 最后两个之母是 er 一般在开源的框架下面都是指的是暴漏给使用者的调用入口
        return new RedisStateMachinePersister<>(p);
    }
}
