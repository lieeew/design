package com.leikooo.design.pay.strategy.factory;

import com.leikooo.design.pay.strategy.PayStrategyInterface;
import com.leikooo.design.pay.strategy.context.PayContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class PayContextFactory extends AbstractPayContextFactory<PayContext> {
    public static final Map<String, PayContext> payContexts = new ConcurrentHashMap<>();

    @Override
    public PayContext getContext(Integer payType) {
        // 根据 type 定位枚举类
        StrategyEnum strategyEnum =
                payType == 1 ? StrategyEnum.alipay : payType == 2 ? StrategyEnum.wechat : null;
        if (null == strategyEnum) {
            throw new UnsupportedOperationException("支付方式不支持!");
        }
        PayContext context = payContexts.get(strategyEnum.name());
        if (context != null) {
            return context;
        }
        try {
            PayStrategyInterface payStrategyInterface = (PayStrategyInterface) Class.forName(strategyEnum.getValue()).getDeclaredConstructor().newInstance();
            PayContext payContext = new PayContext(payStrategyInterface);
            payContexts.put(strategyEnum.name(), payContext);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new UnsupportedOperationException("Get payStrategy error !");
        }
        return payContexts.get(strategyEnum.name());
    }
}
