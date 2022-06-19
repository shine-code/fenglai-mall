package com.fenglai.admin.controller.open;

import com.fenglai.admin.feign.MemberFeignClient;
import com.fenglai.admin.pojo.dtos.MemListVO;
import com.fenglai.common.web.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/open/v1/sys-user")
public class OpenUserController {

    @Autowired
    private MemberFeignClient memberFeignClient;

    @GetMapping("mem-feign")
    public R get() {
        R r = memberFeignClient.listMemberByUserId(1L);
        log.info("会员服务返回===>{}", r);
        MemListVO memListVO = r.convertDataTo(MemListVO.class);
        return R.ok(memListVO);
    }
}
