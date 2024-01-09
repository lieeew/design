package com.leikooo.design.ordermanagement.comand.invoke;

import com.leikooo.design.ordermanagement.comand.OrderCommandInterface;
import com.leikooo.design.pojo.Order;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 调用者类
 */
public class OrderCommandInvoke {

    public void invoke(OrderCommandInterface command, Order order) {
        command.execute(order);
    }

}
