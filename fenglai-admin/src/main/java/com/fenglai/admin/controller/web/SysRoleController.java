package com.fenglai.admin.controller.web;

import com.fenglai.admin.pojo.dtos.AddRoleDTO;
import com.fenglai.admin.pojo.dtos.QueryRoleDTO;
import com.fenglai.admin.pojo.vos.RoleInfoVO;
import com.fenglai.common.web.annotations.PostParam;
import com.fenglai.common.web.response.CommonPage;
import com.fenglai.common.web.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fenglai.admin.service.ISysRoleService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @description: 角色表 Controller
 *
 * @author TJ
 * @date: 2022-05-15
 */
@RestController
@RequestMapping("api/v1/sys-role")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 角色列表
     * @param queryRoleDTO 查询dto
     * @param page 分页对象
     */
    @GetMapping("queryRoleList")
    public R queryRoleList(QueryRoleDTO queryRoleDTO, CommonPage page) {
        List<RoleInfoVO> roleInfoVOS = iSysRoleService.queryRoleList(queryRoleDTO, page);
        return R.ok(roleInfoVOS, page);
    }

    /**
     * 新增/更新角色
     * @param roleDTO 角色dto
     */
    @PostMapping("addAndUpdateRole")
    public R addAndUpdateRole(@RequestBody AddRoleDTO roleDTO) {
        return R.ok(iSysRoleService.addAndUpdateRole(roleDTO));
    }

    /**
     * 启用/禁用/删除角色
     */
    @PostMapping("changeRoleStatus")
    public R changeRoleStatus(@PostParam @NotNull(message = "角色id不能为空") Long id,
                              @PostParam @NotNull(message = "角色状态不能为空") Integer roleStatus) {

        return R.ok(iSysRoleService.changeRoleStatus(id, roleStatus));
    }

    /**
     * 导出角色列表
     */
    @GetMapping("/exportRoleList")
    public void exportRoleList(QueryRoleDTO queryRoleDTO, HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + URLEncoder.encode("角色列表.xlsx", "UTF-8"));
        iSysRoleService.exportRoleList(queryRoleDTO, response.getOutputStream());
    }

    /**
     * 新增角色关联的用户
     * @param roleId 角色id
     * @param userIds 用户id集合
     */
    @PostMapping("addRoleRelateUser")
    public R addRoleRelateUser(@PostParam @NotNull(message = "角色id不能为空") Long roleId,
                               @PostParam @NotNull(message = "用户id不能为空") List<Long> userIds) {


        return R.ok(iSysRoleService.addRoleRelateUser(roleId, userIds));
    }

}
