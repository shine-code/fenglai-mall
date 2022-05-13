package com.fenglai.test;

import cn.hutool.core.util.IdUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.fenglai.test.redis.RedisConfig;
import com.fenglai.test.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-30
 **/
@Slf4j(topic = "fenglai-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RedisTest {

    @Autowired
    RedisService redisService;

    @Test
    void vTest() {
//        int a = 4294967295;
        System.out.println(IdUtil.getSnowflakeNextId());
    }

    @Test
    void cacheTest() {

        User user = new User()
                .setName("小明")
                .setJob(Lists.newArrayList("医生", "学生"));
        Assertions.assertTrue(redisService.set("user", user));

        log.info("user time === " + redisService.getExpire("user"));
        log.info("user == " + redisService.get("user"));

    }

    @Test
    void lockTest() {

        // 设置公共资源
        redisService.set("resource", 10);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> Forest.get("http://localhost:66/test/v1").async().execute());
            executorService.submit(() -> Forest.get("http://localhost:67/test/v1").async().execute());
        }
        while (true) {}
    }
}
