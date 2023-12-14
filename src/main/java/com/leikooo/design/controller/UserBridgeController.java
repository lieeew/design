package com.leikooo.design.controller;

import com.leikooo.design.adapter.Login3rdAdapter;
import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.service.UserBridgeService;
import com.leikooo.design.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@RestController
@RequestMapping("/bridge")
public class UserBridgeController {

    @Resource
    private UserBridgeService userBridgeService;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userBridgeService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userBridgeService.register(userInfo);
    }

    @GetMapping("/gitee")
    public String register(HttpServletRequest request) {
        return userBridgeService.login3rd(request, "GITEE");
    }
}
