package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysOrgDO;
import com.fenglai.admin.mapper.SysOrgMapper;
import com.fenglai.admin.service.ISysOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 组织单位表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrgDO> implements ISysOrgService {

     @Autowired
     private SysOrgMapper sysOrgMapper;
}
