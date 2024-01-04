package com.leikooo.design.bridge.abst.factory;

import com.leikooo.design.bridge.abst.AbstractRegisterLoginComponent;
import com.leikooo.design.bridge.abst.RegisterLoginComponent;
import com.leikooo.design.bridge.function.RegisterLoginFuncInterface;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class RegisterLoginComponentFactory {
    // 缓存左路 AbstractRegisterLoginComponent, 根据不同的登录方式缓存
    public static Map<String, AbstractRegisterLoginComponent> componentMap = new ConcurrentHashMap<>();

    // 缓存右路，比如 RegisterLoginComponent ,RegisterLoginByGitee
    public static Map<String, RegisterLoginFuncInterface> funcMap = new ConcurrentHashMap<>();

    // 获取特定的 component
    public static AbstractRegisterLoginComponent getRegisterLoginComponent(String type) {
        AbstractRegisterLoginComponent component = componentMap.get(type);
        if (component == null) {
            synchronized (componentMap) {
                // 需要加一个双重校验
                if (component == null) {
                    component = new RegisterLoginComponent(funcMap.get(type));
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }
}

