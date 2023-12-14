package com.leikooo.design.bridge.function;

import com.leikooo.design.bridge.abst.factory.RegisterLoginComponentFactory;
import com.leikooo.design.pojo.UserInfo;
import com.leikooo.design.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@Component
public class RegisterLoginByDefault extends AbstractRegisterLoginFunc implements RegisterLoginFuncInterface {

    @Resource
    private UserRepository userRepository;

    // RegisterLoginByDefault 注册到容器之中之后我们就，只要被 @PostConstruct Spring 就会立刻执行这个方法
    @PostConstruct
    public void initFuncMap() {
        RegisterLoginComponentFactory.funcMap.put("DEFAULT", this);
    }

    @Override
    public String login(String account, String password) {
        return super.commonLogin(account, password, userRepository);
    }

    @Override
    public String register(UserInfo userInfo) {
        return super.commonRegister(userInfo, userRepository);
    }

    @Override
    public boolean checkUserExist(String account) {
        return super.commonCheckUserExist(account, userRepository);
    }
}
