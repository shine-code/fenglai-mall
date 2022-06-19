package com.fenglai.admin.feign;

import com.fenglai.common.web.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-06-19
 **/
@FeignClient(value = "mall-member", path = "api/v1/member")
public interface MemberFeignClient {

    @GetMapping("listMember")
    R listMemberByUserId(@RequestParam("userId") Long userId);
}
