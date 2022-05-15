package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysRoleDO;
import com.fenglai.admin.mapper.SysRoleMapper;
import com.fenglai.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 角色表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements ISysRoleService {

     @Autowired
     private SysRoleMapper sysRoleDO;
}
