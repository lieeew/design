package com.leikooo.design.ordermanagement.comand;

import com.leikooo.design.ordermanagement.comand.receiver.OrderCommandReceiver;
import com.leikooo.design.pojo.Order;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class OrderCommand implements OrderCommandInterface {
    @Resource
    private OrderCommandReceiver receiver;

    @Override
    public void execute(Order order) {
        this.receiver.action(order);
    }
}
