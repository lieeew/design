package com.leikooo.design.adapter;

import com.leikooo.design.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget {

    @Value("${gitee.state}")
    private String giteeState;

    @Value("${gitee.token.url}")
    private String giteeTokenUrl;

    @Value("${gitee.user.url}")
    private String giteeUserUrl;

    private String giteePrefix;
    @Override
    public String loginByGitee(String code, String state) {
        return null;
    }

    @Override
    public String loginByGithub(String... parameters) {
        return null;
    }

    @Override
    public String loginByWeChat(String... parameters) {
        return null;
    }
}
