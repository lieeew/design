package com.leikooo.design;

import com.leikooo.design.pojo.Order;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class StringTest {

    @Test
    void test() {
        Order order = new Order();
        String s = """
                {"out_trace_no": "%s",}
                """;
        String bizContent = "{\"out_trade_no\":\"" + order.getOrderId() + "\","
                + "\"total_amount\":\"" + order.getPrice() + "\","
                + "\"subject\":\"" + "伟山育琪" + "\","
                + "\"body\":\"" + "商品描述" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        String format = String.format(s, 12);
        System.out.println(format);
    }
}
