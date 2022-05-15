package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysPermissionDO;
import com.fenglai.admin.mapper.SysPermissionMapper;
import com.fenglai.admin.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 权限表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionDO> implements ISysPermissionService {

     @Autowired
     private SysPermissionMapper sysPermissionDO;
}
