package com.fenglai.admin.service;

import com.fenglai.admin.pojo.dos.SysRoleDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fenglai.admin.pojo.dtos.AddRoleDTO;
import com.fenglai.admin.pojo.dtos.QueryRoleDTO;
import com.fenglai.admin.pojo.vos.RoleInfoVO;
import com.fenglai.common.web.response.CommonPage;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * @description: 角色表 - Service服务
 *
 * @author TJ
 * @date: 2022-05-15
 */
public interface ISysRoleService extends IService<SysRoleDO> {

    List<RoleInfoVO> queryRoleList(QueryRoleDTO queryRoleDTO, CommonPage page);

    boolean addAndUpdateRole(AddRoleDTO roleDTO);

    boolean changeRoleStatus(Long id, Integer roleStatus);

    void exportRoleList(QueryRoleDTO queryRoleDTO, ServletOutputStream outputStream);

    boolean addRoleRelateUser(Long roleId, List<Long> userIds);
}
