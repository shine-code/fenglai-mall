package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysUserRoleDO;
import com.fenglai.admin.mapper.SysUserRoleMapper;
import com.fenglai.admin.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 用户角色表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleDO> implements ISysUserRoleService {

     @Autowired
     private SysUserRoleMapper sysUserRoleMapper;
}
