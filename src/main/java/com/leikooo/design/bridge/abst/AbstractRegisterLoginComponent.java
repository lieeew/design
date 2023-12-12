package com.leikooo.design.bridge.abst;

import com.leikooo.design.bridge.function.RegisterLoginFuncInterface;
import com.leikooo.design.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public abstract class AbstractRegisterLoginComponent {
    protected RegisterLoginFuncInterface funcInterface;

    public AbstractRegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        validate(funcInterface);
        this.funcInterface = funcInterface;
    }

    protected abstract String login(String account, String password);

    protected abstract String register(UserInfo userInfo);

    protected abstract boolean checkUserExist(String account);

    protected abstract String login3rd(HttpServletRequest request);


    protected final void validate(RegisterLoginFuncInterface funcInterface) {
        if (!(funcInterface instanceof RegisterLoginFuncInterface)) {
            throw new UnsupportedOperationException("Unknown register/ login function type!");
        }
    }
}
