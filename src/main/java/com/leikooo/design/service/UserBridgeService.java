package com.leikooo.design.service;

import com.leikooo.design.bridge.abst.AbstractRegisterLoginComponent;
import com.leikooo.design.bridge.abst.RegisterLoginComponent;
import com.leikooo.design.bridge.abst.factory.RegisterLoginComponentFactory;
import com.leikooo.design.bridge.function.RegisterLoginByDefault;
import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.repo.UserRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.file.LinkOption;
import java.util.Date;


/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Service
public class UserBridgeService {
    @Resource
    private UserRepository userRepository;

    public String login(String account, String password) {
//        这样每一次调用就会创建两个对象，对垃圾回收的压力太高, 我们需要优化一下
//        AbstractRegisterLoginComponent registerLoginComponent = new RegisterLoginComponent(new RegisterLoginByDefault());
//        return  registerLoginComponent.login(account, password);
        AbstractRegisterLoginComponent loginComponent = RegisterLoginComponentFactory.getRegisterLoginComponent("DEFAULT");
        return loginComponent.login(account, password);
    }

    public String register(UserInfo userInfo) {
        AbstractRegisterLoginComponent loginComponent = RegisterLoginComponentFactory.getRegisterLoginComponent("DEFAULT");
        return loginComponent.register(userInfo);
    }

    public String login3rd(HttpServletRequest request, String type) {
        AbstractRegisterLoginComponent loginComponent = RegisterLoginComponentFactory.getRegisterLoginComponent(type);
        return loginComponent.login3rd(request);
    }


    /**
     * @param userName
     * @return true 存在 ; false 不存在
     */
    public boolean checkUserExists(String userName) {
        UserInfo byUserName = userRepository.findByUserName(userName);
        return byUserName != null;
    }
}
