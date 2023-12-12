package com.leikooo.design.bridge.function;

import com.alibaba.fastjson2.JSONObject;
import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.repo.UserRepository;
import com.leikooo.design.utils.HttpClientUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class RegisterLoginByGitee implements RegisterLoginFuncInterface {
    @Resource
    private UserRepository userRepository;

    @Value("${gitee.state}")
    private String giteeState;

    @Value("${gitee.token.url}")
    private String giteeTokenUrl;

    @Value("${gitee.user.url}")
    private String giteeUserUrl;

    @Value("${gitee.user.prefix}")
    private String giteePrefix;

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
    public String login3rd(HttpServletRequest request) {
        String state = request.getParameter("state");
        String code = request.getParameter("code");
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("Invalid state !");
        }
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        assert tokenResponse != null : "tokenResponse is null!";
        String token = tokenResponse.getString("access_token");
        String userUrl = giteeUserUrl.concat(token);
        JSONObject userResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);
        assert userResponse != null : "userResponse is null!";
        String userName = giteePrefix + userResponse.getString("name");
        // password 和 username 保持一致
        String password = userName;
        return autoRegister3rdAndLogin(userName, password);
    }

    private String autoRegister3rdAndLogin(String userName, String password) {
        if (StringUtils.isAnyEmpty(userName, password)) {
            throw new UnsupportedOperationException("Invalid userName or password!");
        }
        UserInfo userInfo = new UserInfo(userName, password);
        if (!checkUserExist(userName)) {
            register(userInfo);
        }
        return login(userName, password);
    }

    @Override
    public boolean checkUserExist(String account) {
        UserInfo byUserName = userRepository.findByUserName(account);
        return byUserName != null;
    }
}
