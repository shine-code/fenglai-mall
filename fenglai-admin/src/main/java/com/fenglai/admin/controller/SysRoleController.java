package com.fenglai.admin.controller;

import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fenglai.admin.service.ISysRoleService;

/**
 * @description: 角色表 Controller
 *
 * @author TJ
 * @date: 2022-05-13
 */
@RestController
@RequestMapping("api/v1/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

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
