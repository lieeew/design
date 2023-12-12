package com.leikooo.design.bridge.function;

import com.leikooo.design.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 修复一些瑕疵，需要我们在其他地方实现对应的代码
 */
public interface RegisterLoginFuncInterface {
    String login(String account, String password);

    String register(UserInfo userInfo);

    boolean checkUserExist(String account);

    String login3rd(HttpServletRequest request);
}
