package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysUserGroupRefDO;
import com.fenglai.admin.mapper.SysUserGroupRefMapper;
import com.fenglai.admin.service.ISysUserGroupRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 用户-用户组关联 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysUserGroupRefServiceImpl extends ServiceImpl<SysUserGroupRefMapper, SysUserGroupRefDO> implements ISysUserGroupRefService {

     @Autowired
     private SysUserGroupRefMapper sysUserGroupRefMapper;
}
