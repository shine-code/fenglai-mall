package com.fenglai.admin.service;

import com.fenglai.admin.pojo.dos.SysUserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fenglai.admin.pojo.dtos.AddUserDTO;

/**
 * @description: 用户表 - Service服务
 *
 * @author TJ
 * @date: 2022-05-15
 */
public interface ISysUserService extends IService<SysUserDO> {

    boolean addUser(AddUserDTO userDTO);

    boolean updateUser(AddUserDTO userDTO);

    boolean changeUserStatus(Long userId, Integer userStatus);
}
