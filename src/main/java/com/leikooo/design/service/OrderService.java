package com.leikooo.design.service;

import com.leikooo.design.ordermanagement.comand.OrderCommand;
import com.leikooo.design.ordermanagement.comand.invoke.OrderCommandInvoke;
import com.leikooo.design.ordermanagement.state.OrderState;
import com.leikooo.design.ordermanagement.state.OrderStateChangeAction;
import com.leikooo.design.pay.face.PayFace;
import com.leikooo.design.pojo.Order;
import com.leikooo.design.utils.RedisCommonProcessor;
import jakarta.annotation.Resource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.data.redis.RedisStateMachinePersister;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Service
public class OrderService {
    @Resource
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;
    @Resource
    private RedisCommonProcessor redisCommonProcessor;
    @Resource
    private RedisStateMachinePersister<OrderState, OrderStateChangeAction> redisStateMachinePersister;
    @Resource
    private OrderCommand orderCommand;
    @Resource
    private PayFace payFace;

    public Order creatOrder(String productId) {
        String orderId = "OID" + productId;
        Order order = Order.builder()
                .productId(productId)
                .orderId(orderId)
                .orderState(OrderState.ORDER_WAIT_PAY)
                .build();
        redisCommonProcessor.set(orderId, order, 900);
        OrderCommandInvoke invoke = new OrderCommandInvoke();
        invoke.invoke(orderCommand, order);
        return order;
    }

    public Order pay(String orderId) {
        var order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.PAY_ORDER)
                .setHeader("order", order)
                .build();
        if (changeStateAction(message, order)) {
            return (Order) redisCommonProcessor.get(orderId);
        }
        return null;
    }

    public Order send(String orderId) {
        var order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.SEND_ORDER)
                .setHeader("order", order)
                .build();
        if (changeStateAction(message, order)) {
            return (Order) redisCommonProcessor.get(orderId);
        }
        return null;
    }

    public Order receive(String orderId) {
        var order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder
                .withPayload(OrderStateChangeAction.RECEIVE_ORDER)
                .setHeader("order", order)
                .build();
        if (changeStateAction(message, order)) {
            return (Order) redisCommonProcessor.get(orderId);
        }
        return null;
    }

    public String getPayUrl(String orderId, Float price, Integer payType) {
        var order = (Order) redisCommonProcessor.get(orderId);
        Order updateOrder = Order.builder().orderState(order.getOrderState()).productId(order.getProductId()).orderId(order.getOrderId()).price(price).build();
        return payFace.pay(updateOrder, payType);
    }

    private boolean changeStateAction(Message<OrderStateChangeAction> message, Order order) {
        try {
            orderStateMachine.start();
            // 从 redis 中获取状态机，缓存的 key 是 orderId + "STATE"
            // 这里的 bug 就是 redis 里面的 machine 在测试之前必须删除
            redisStateMachinePersister.restore(orderStateMachine, order.getOrderId() + "STATE");
            // 把 Message 发给 OrderStateListener
            boolean result = orderStateMachine.sendEvent(message);
            // 修改完成订单状态 状态机 存储到 redis 之中
            redisStateMachinePersister.persist(orderStateMachine, order.getOrderId() + "STATE");
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            orderStateMachine.stop();
        }
    }
}
