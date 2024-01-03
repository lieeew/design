package com.leikooo.design.pay.strategy;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.leikooo.design.pojo.Order;
import com.leikooo.design.utils.Constants;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class AlipayStrategy implements PayStrategyInterface {
    @Override
    public String pay(Order order) {
        //  创建 alipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(Constants.ALIPAY_GATEWAY, Constants.APP_ID, Constants.APP_PRIVATE_KEY, "JSON", "UTF-8", Constants.APP_PUBLIC_KEY, Constants.SIGN_TYPE);
        // 设置请求参数
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(Constants.CALLBACK_URL);
        payRequest.setBizContent("{\"out_trade_no\":\"" + order.getOrderId() + "\","
                + "\"total_amount\":\"" + order.getPrice() + "\","
                + "\"subject\":\"" + "leikooo" + "\","
                + "\"body\":\"" + "商品描述" + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 请求
        try {
            return alipayClient.pageExecute(payRequest, "GET").getBody();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Alipay failed! " + e);
        }
    }
}
