package com.fenglai.admin.controller;

import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fenglai.admin.service.ISysUserGroupRefService;

/**
 * @description: 用户-用户组关联 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@RestController
@RequestMapping("api/v1/sys-user-group-ref-do")
public class SysUserGroupRefController {

    @Autowired
    private ISysUserGroupRefService iSysUserGroupRefService;

}
