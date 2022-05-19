package com.fenglai.admin.controller;

import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fenglai.admin.service.ISysUserService;

import javax.validation.constraints.NotNull;

/**
 * @description: 用户表 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Validated
@RestController
@RequestMapping("api/v1/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 新增用户
     * @param userDTO 用户对象
     * @return R
     */
    @PostMapping("addUser")
    public R addUser(@Validated @RequestBody AddUserDTO userDTO) {
        boolean res = iSysUserService.addUser(userDTO);
        return R.judge(res);
    }

    /**
     * 更新用户
     * @param userDTO 用户对象
     * @return R
     */
    @PostMapping("updateUser")
    public R updateUser(@Validated @RequestBody AddUserDTO userDTO) {
        boolean res = iSysUserService.updateUser(userDTO);
        return R.judge(res);
    }

    /**
     * 更新用户状态, 启用/停用/删除
     * @param id 用户id
     * @param userStatus 用户状态值
     * @return R
     */
    @PostMapping("changeUserStatus")
    public R changeUserStatus(@NotNull(message = "用户id不能为空") Long id,
                              @NotNull(message = "用户状态不能为空") Integer userStatus) {
        return R.ok(iSysUserService.changeUserStatus(id, userStatus));
    }


}
