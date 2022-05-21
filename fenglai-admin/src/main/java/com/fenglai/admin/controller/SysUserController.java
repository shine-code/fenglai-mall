package com.fenglai.admin.controller;

import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.admin.pojo.dtos.QueryUserDTO;
import com.fenglai.admin.pojo.vos.SysUserListVO;
import com.fenglai.common.web.annotations.PostParam;
import com.fenglai.common.web.response.Page;
import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.fenglai.admin.service.ISysUserService;

import javax.validation.constraints.NotNull;
import java.util.List;

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
     * 用户列表分页查询
     * @param queryUserDTO 查询参数
     * @param page 分页参数
     * @return R
     */
    @GetMapping("queryUserList")
    public R queryUserList(QueryUserDTO queryUserDTO, Page page) {
        List<SysUserListVO> listVOS = iSysUserService.queryUserList(queryUserDTO, page);
        return R.ok(listVOS, page);
    }

    /**
     * 新增用户
     * @param userDTO 用户对象
     * @return R
     */
    @PostMapping("addUser")
    public R addUser(@Validated @RequestBody AddUserDTO userDTO) {
        return R.judge(iSysUserService.addUser(userDTO));
    }

    /**
     * 更新用户
     * @param userDTO 用户对象
     * @return R
     */
    @PostMapping("updateUser")
    public R updateUser(@Validated @RequestBody AddUserDTO userDTO) {
        return R.judge(iSysUserService.updateUser(userDTO));
    }

    /**
     * 更新用户状态, 启用/停用/删除
     * @param id 用户id
     * @param userStatus 用户状态值, {@link com.fenglai.admin.pojo.enums.UserStatusEnum}
     * @return R
     */
    @PostMapping("changeUserStatus")
    public R changeUserStatus(@PostParam @NotNull(message = "用户id不能为空") Long id,
                              @PostParam @NotNull(message = "用户状态不能为空") Integer userStatus) {
        return R.judge(iSysUserService.changeUserStatus(id, userStatus));
    }


}
