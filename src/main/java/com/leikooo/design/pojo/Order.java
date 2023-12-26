package com.leikooo.design.pojo;

import com.leikooo.design.ordermanagement.state.OrderState;
import lombok.*;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Order {
    private String orderId;

    private String productId;
    // 订单状态
    private OrderState orderState;
    // 订单价格
    private Float price;
}
