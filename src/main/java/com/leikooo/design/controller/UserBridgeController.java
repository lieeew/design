package com.leikooo.design.controller;

import com.leikooo.design.adapter.Login3rdAdapter;
import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@RestController
@RequestMapping("/bridge")
public class UserBridgeController {
    @Resource
    private UserService userService;

    @Resource
    private Login3rdAdapter login3rdAdapter;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userService.register(userInfo);
    }
    @GetMapping("/bridge/gitee")
    public String register(String code, String state) {
        return login3rdAdapter.loginByGitee(code, state);
    }

}
