package com.leikooo.design.dprecated.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeprecatedOrder {
    // 订单的唯一 ID
    private String orderId;
    // 商品信息
    private String productId;
    // 订单状态
    private String state;
}
