package com.fenglai.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fenglai.admin.pojo.dos.SysUserDO;
import com.fenglai.admin.mapper.SysUserMapper;
import com.fenglai.admin.pojo.dos.SysUserRoleDO;
import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.admin.pojo.enums.UserStatusEnum;
import com.fenglai.admin.service.ISysUserRoleService;
import com.fenglai.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fenglai.common.web.utils.EnumUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 用户表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {

     @Autowired
     private SysUserMapper sysUserMapper;
     @Autowired
     private ISysUserRoleService iSysUserRoleService;

     @Override
     public boolean addUser(AddUserDTO userDTO) {
          SysUserDO sysUserDO = new SysUserDO();
          BeanUtil.copyProperties(userDTO, sysUserDO, "id", "roleIds");
          boolean res = save(sysUserDO);
          // 添加用户-角色关联关系
          if (res) {
               Long userId = sysUserDO.getId();
               List<Long> roleIds = userDTO.getRoleIds();
               if (CollUtil.isNotEmpty(roleIds)) {
                    List<SysUserRoleDO> userRoleDOS = roleIds.stream()
                            .map(roleId ->
                                    new SysUserRoleDO()
                                            .setUserId(userId)
                                            .setRoleId(roleId))
                            .collect(Collectors.toList());
                    iSysUserRoleService.saveBatch(userRoleDOS);
               }
          }
          return res;
     }

     @Override
     public boolean updateUser(AddUserDTO userDTO) {
          Long userId = userDTO.getId();
          Assert.notNull(userId, "用户id不能为空");

          SysUserDO existUser = getById(userId);
          Assert.notNull(existUser, "用户不存在");

          // 用户原角色id
          List<Long> oldRoleIds = iSysUserRoleService.list(
                  Wrappers.lambdaQuery(SysUserRoleDO.class)
                          .eq(SysUserRoleDO::getUserId, userId))
                  .stream()
                  .map(SysUserRoleDO::getRoleId)
                  .collect(Collectors.toList());

          List<Long> newRoleIds = userDTO.getRoleIds();

          // 新增的角色id
          List<Long> addUserRoles = CollUtil.subtractToList(newRoleIds, oldRoleIds);
          List<SysUserRoleDO> addUserRoleDOS = addUserRoles.stream()
                  .map(roleId -> new SysUserRoleDO()
                          .setUserId(userId)
                          .setRoleId(roleId))
                  .collect(Collectors.toList());
          iSysUserRoleService.saveBatch(addUserRoleDOS);

          // 需要删除的角色id
          List<Long> delUserRoles = CollUtil.subtractToList(oldRoleIds, newRoleIds);
          if (CollUtil.isNotEmpty(delUserRoles)) {
               iSysUserRoleService.remove(Wrappers.lambdaQuery(SysUserRoleDO.class)
                       .eq(SysUserRoleDO::getUserId, userId)
                       .in(SysUserRoleDO::getRoleId, delUserRoles));
          }

          SysUserDO updateDO = new SysUserDO();
          BeanUtil.copyProperties(userDTO, updateDO, "id", "roleIds");
          return update(updateDO,
                  Wrappers.lambdaUpdate(SysUserDO.class)
                          .eq(SysUserDO::getId, userId));
     }

     @Override
     public boolean changeUserStatus(Long userId, Integer userStatus) {
          boolean exist = EnumUtil.isExist(UserStatusEnum.values(), userStatus);
          Assert.isTrue(exist, "用户状态不存在");
          Assert.notNull(getById(userId), "用户不存在");

          return update(new SysUserDO(), Wrappers.lambdaUpdate(SysUserDO.class)
                  .eq(SysUserDO::getId, userId)
                  .set(SysUserDO::getUserStatus, userStatus));
     }
}
