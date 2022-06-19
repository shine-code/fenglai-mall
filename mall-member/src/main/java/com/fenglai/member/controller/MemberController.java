package com.fenglai.member.controller;

import com.fenglai.common.web.response.R;
import com.fenglai.member.pojo.vos.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-19
 **/
@Slf4j
@RestController
@RequestMapping("api/v1/member")
public class MemberController {

    @GetMapping("listMember")
    public R memberList(Long userId) {
        MemberVO vo = new MemberVO()
                .setId(1L)
                .setName("但是苦尽发");
        try {
            Thread.sleep(2000);
            log.info("sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("member1");
        return R.ok(vo);
    }
}
