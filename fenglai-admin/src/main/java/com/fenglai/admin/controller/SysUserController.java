package com.fenglai.admin.controller;

import com.fenglai.admin.pojo.dos.SysUser;
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
 * @date: 2022-05-13
 */
@RestController
@RequestMapping("api/v1/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 列表查询
     */
    @GetMapping(value = "list")
    public R list(){
        return R.ok();
    }

    /**
     * 根据id查询详情
     */
    @GetMapping(value = "getById")
    public R getById(Long id){
        return R.ok();
    }

    /**
     * 新增数据
     */
    @PostMapping(value = "add")
    public R add(){
        SysUser user = new SysUser()
                .setUserName("aaa");
        iSysUserService.save(user);
        return R.ok();
    }

    /**
     * 根据id删除
     */
    @GetMapping(value = "deleteById")
    public R delete(Long id){
        return R.ok();
    }

}
