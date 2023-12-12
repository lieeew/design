package com.leikooo.design.bridge.abst;

import com.leikooo.design.bridge.function.RegisterLoginFuncInterface;
import com.leikooo.design.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
public class RegisterLoginComponent extends AbstractRegisterLoginComponent {

    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    @Override
    public String login(String account, String password) {
        return funcInterface.login(account, password);
    }

    @Override
    public String register(UserInfo userInfo) {
        return funcInterface.register(userInfo);
    }

    @Override
    public boolean checkUserExist(String account) {
        return funcInterface.checkUserExist(account);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
