package com.leikooo.design.ordermanagement.comand;

import com.leikooo.design.pojo.Order;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public interface OrderCommandInterface {

    /**
     * 执行先关的命令
     *
     * @param order
     */
    void execute(Order order);

}
