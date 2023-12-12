package com.leikooo.design.service;

import com.leikooo.design.bridge.abst.AbstractRegisterLoginComponent;
import com.leikooo.design.bridge.abst.RegisterLoginComponent;
import com.leikooo.design.bridge.function.RegisterLoginByDefault;
import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.repo.UserRepository;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

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
        AbstractRegisterLoginComponent registerLoginComponent = new RegisterLoginComponent(new RegisterLoginByDefault());
        return  registerLoginComponent.login(account, password);
    }

    public String register(UserInfo userInfo) {
        return null;
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
