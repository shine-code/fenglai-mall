package com.fenglai.admin.controller.web;

import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fenglai.admin.service.ISysOrgService;

/**
 * @description: 组织单位表 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@RestController
@RequestMapping("api/v1/sys-org")
public class SysOrgController {

    @Autowired
    private ISysOrgService iSysOrgService;

}
