package com.fenglai.test;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-15
 **/
@Slf4j
public class UtilTest {

    public static void main(String[] args) {

        beanCopyTest();
    }

    static void beanCopyTest() {
        List<User> sourceUser = new ArrayList<>(4096);
        for (int i = 0; i < 8000; i++) {
            sourceUser.add(new User()
                    .setName("将进酒" + i)
                    .setJob(Collections.singletonList("医生" + i))
                    .setId(i));
        }

        long t1 = System.currentTimeMillis();
        List<User> targetUser = new ArrayList<>(4096);
        sourceUser.forEach(user -> {
            User temp = new User();
            BeanUtil.copyProperties(user, temp);
            targetUser.add(temp);
        });

        long t2 = System.currentTimeMillis();
        List<User> targetUser2 = new ArrayList<>(4096);
        sourceUser.forEach(user -> {
            User temp = new User();
            BeanUtils.copyProperties(user, temp);
            targetUser2.add(temp);
        });
        long t3 = System.currentTimeMillis();

        log.info("hutool: {}, spring: {}", t2-t1, t3-t2);
    }
}
