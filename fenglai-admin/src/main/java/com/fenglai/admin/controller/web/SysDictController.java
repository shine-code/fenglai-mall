package com.fenglai.admin.controller.web;

import com.fenglai.common.web.response.CommonPage;
import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fenglai.admin.service.ISysDictService;

/**
 * @description: 字典表 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@RestController
@RequestMapping("api/v1/sys-dict")
public class SysDictController {

    @Autowired
    private ISysDictService iSysDictService;

    /**
     * 字典数据列表查询
     * @return R
     */
    @GetMapping("getDictList")
    public R getDictList(String dictKeyword, String itemKeyword, CommonPage page) {
        return R.ok(iSysDictService.getDictList(dictKeyword, itemKeyword, page), page);
    }

}
