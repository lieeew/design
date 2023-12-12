package com.leikooo.design.bridge.impr.abst;

import com.leikooo.design.bridge.impr.function.RegisterLoginFuncInterface;
import com.leikooo.design.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class RegisterLoginComponent extends AbstractRegisterLoginComponent {

    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    @Override
    protected String login(String account, String password) {
        return funcInterface.login(account, password);
    }

    @Override
    protected String register(UserInfo userInfo) {
        return funcInterface.register(userInfo);
    }

    @Override
    protected boolean checkUserExist(String account) {
        return funcInterface.checkUserExist(account);
    }


    @Override
    protected String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
