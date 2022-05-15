package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysRolePermissionDO;
import com.fenglai.admin.mapper.SysRolePermissionMapper;
import com.fenglai.admin.service.ISysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 角色权限表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionDO> implements ISysRolePermissionService {

     @Autowired
     private SysRolePermissionMapper sysRolePermissionDO;
}
