package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysUserDO;
import com.fenglai.admin.mapper.SysUserMapper;
import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 用户表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {

     @Autowired
     private SysUserMapper sysUserDO;

     @Override
     public boolean addUser(AddUserDTO userDTO) {

          return false;
     }
}
