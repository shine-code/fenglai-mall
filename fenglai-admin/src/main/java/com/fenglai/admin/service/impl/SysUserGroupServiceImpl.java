package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysUserGroupDO;
import com.fenglai.admin.mapper.SysUserGroupMapper;
import com.fenglai.admin.service.ISysUserGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 用户组 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroupDO> implements ISysUserGroupService {

     @Autowired
     private SysUserGroupMapper sysUserGroupDO;
}
