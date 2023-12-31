package com.leikooo.design.pay.strategy.factory;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public enum StrategyEnum {
    alipay("com.leikooo.design.pay.strategy.AlipayStrategy"),
    wechat("com.leikooo.design.pay.strategy.WeChatPayStrategy");
    private String value;
    StrategyEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}
