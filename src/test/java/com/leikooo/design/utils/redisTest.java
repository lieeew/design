package com.leikooo.design.utils;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author <a href="https://github.com/lieeew">leikooo</a>
 * @Description
 */
@SpringBootTest
public class redisTest {

    @Resource
    private RedisCommonProcessor redisCommonProcessor;

    @Test
    void test() {
        redisCommonProcessor.set("leikooo", "学习", 60);
        Object o = redisCommonProcessor.get("leikooo");
        System.out.println(o);
    }
}
