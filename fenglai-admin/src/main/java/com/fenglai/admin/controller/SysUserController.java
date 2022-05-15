package com.fenglai.admin.controller;

import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fenglai.admin.service.ISysUserService;

/**
 * @description: 用户表 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@RestController
@RequestMapping("api/v1/sys-user-do")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 新增用户
     * @param userDTO 用户对象
     * @return R
     */
    @PostMapping("addUser")
    public R addUser(AddUserDTO userDTO) {
        boolean res = iSysUserService.addUser(userDTO);
        return R.judge(res);
    }

}
