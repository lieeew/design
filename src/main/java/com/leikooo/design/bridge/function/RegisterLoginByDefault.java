package com.leikooo.design.bridge.function;

import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.repo.UserRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.net.http.HttpRequest;
import java.util.Date;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class RegisterLoginByDefault implements RegisterLoginFuncInterface {

    @Resource
    private UserRepository userRepository;

    @Override
    public String login(String account, String password) {
        assert StringUtils.isAnyEmpty(account, password) : "account or password do not follow our request";
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "account / password error !";
        }
        return "loginSuccess";
    }

    @Override
    public String register(UserInfo userInfo) {
        if (this.checkUserExist(userInfo.getUserName())) {
            throw new RuntimeException("user already exists");
        }
        userInfo.setCreatData(new Date());
        userRepository.save(userInfo);
        return "Register success";

    }

    @Override
    public boolean checkUserExist(String account) {
        UserInfo byUserName = userRepository.findByUserName(account);
        return byUserName != null;
    }

    /**
     * 这里是小瑕疵，不该需要在这里实现
     *
     * @param request
     * @return
     */
    @Override
    public String login3rd(HttpServletRequest request) {
        return null;
    }
}
