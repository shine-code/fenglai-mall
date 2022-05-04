package com.fenglai.test;

import cn.hutool.core.convert.Convert;
import com.fenglai.test.redis.RedisService;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-30
 **/
@RestController
@RequestMapping("test")
public class IndexController {

    @Autowired
    RedisService redisService;
    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/v1")
    public void lockTest() {

        // 获取分布式锁, 只要锁的名字一样, 就是同一把锁
        RLock lock = redissonClient.getLock("lock");
        // 阻塞获取锁, 默认过期时间30s
        lock.lock(2, TimeUnit.SECONDS);

        new RedissonRedLock();
        try{

            Object resource = redisService.get("resource");
            System.out.println("resource == " + resource);
            Thread.sleep(3000);
            Integer integer = Convert.toInt(resource);
            if (integer > 0) {
                redisService.set("resource", integer - 1);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
