package com.leikooo.design.bridge.function;

import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description 去除瑕疵的关键代码
 */
public abstract class AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    /**
     * commonLogin 登录方法, 这里使用 protected 修饰「只允许子类使用」
     *
     * @param account
     * @param password
     * @return
     */
    protected String commonLogin(String account, String password, UserRepository userRepository) {
        assert StringUtils.isAnyEmpty(account, password) : "account or password do not follow our request";
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "account / password error !";
        }
        return "loginSuccess";
    }

    protected String commonRegister(UserInfo userInfo, UserRepository userRepository) {
        if (this.commonCheckUserExist(userInfo.getUserName(), userRepository)) {
            throw new RuntimeException("user already exists");
        }
        userInfo.setCreatData(new Date());
        userRepository.save(userInfo);
        return "Register success";
    }

    protected boolean commonCheckUserExist(String userName, UserRepository userRepository) {
        return userRepository.findByUserName(userName)!= null;
    }

    @Override
    public String login(String account, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String register(UserInfo userInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean checkUserExist(String account) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }
}
