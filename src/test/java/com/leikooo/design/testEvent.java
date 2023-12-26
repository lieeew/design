package com.leikooo.design;

import com.leikooo.design.ordermanagement.state.OrderState;
import com.leikooo.design.ordermanagement.state.OrderStateChangeAction;
import com.leikooo.design.pojo.Order;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */

@SpringBootTest
public class testEvent {

    @Resource
    private StateMachine<OrderState, OrderStateChangeAction> stateMachine;

    @Test
    void test() {
        stateMachine.start();
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.PAY_ORDER)
                .setHeader("order", new Order())
                .build();
        stateMachine.sendEvent(message);
        stateMachine.stop();
    }

}
