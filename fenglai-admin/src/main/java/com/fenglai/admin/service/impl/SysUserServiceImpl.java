package com.fenglai.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fenglai.admin.pojo.dos.SysUserDO;
import com.fenglai.admin.mapper.SysUserMapper;
import com.fenglai.admin.pojo.dos.SysUserRoleDO;
import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.admin.pojo.dtos.ImportUserDTO;
import com.fenglai.admin.pojo.dtos.QueryUserDTO;
import com.fenglai.admin.pojo.enums.UserSexEnum;
import com.fenglai.admin.pojo.enums.SysStatusEnum;
import com.fenglai.admin.pojo.vos.ExportUserVO;
import com.fenglai.admin.pojo.vos.RoleRelateUserVO;
import com.fenglai.admin.pojo.vos.SysUserListVO;
import com.fenglai.admin.service.ISysUserRoleService;
import com.fenglai.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fenglai.common.core.excel.ExcelFailResult;
import com.fenglai.common.core.utils.ExcelUtil;
import com.fenglai.common.web.response.CommonPage;
import com.fenglai.common.web.utils.EnumUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
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
     private PasswordEncoder passwordEncoder;
     @Autowired
     private SysUserMapper sysUserMapper;
     @Autowired
     private ISysUserRoleService iSysUserRoleService;

     @Override
     public List<SysUserListVO> queryUserList(QueryUserDTO queryUserDTO, CommonPage page) {

          PageHelper.startPage(page.getPageNum(), page.getPageSize());
          List<SysUserListVO> resList = sysUserMapper.queryUserList(queryUserDTO);
          page.setTotal(new PageInfo<>(resList).getTotal());
          return resList;
     }

     @Override
     @Transactional(rollbackFor = Exception.class)
     public boolean addUser(AddUserDTO userDTO) {
          boolean exists = baseMapper.exists(Wrappers.lambdaQuery(SysUserDO.class)
                  .eq(SysUserDO::getUserCode, userDTO.getUserCode()));
          Assert.isFalse(exists, "该用户编码已存在");

          SysUserDO sysUserDO = new SysUserDO();
          BeanUtil.copyProperties(userDTO, sysUserDO, "id", "roleIds");
          // 默认密码
          sysUserDO.setPassword(passwordEncoder.encode("123456"));
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
                    res = iSysUserRoleService.saveBatch(userRoleDOS);
               }
          }
          return res;
     }

     @Override
     @Transactional(rollbackFor = Exception.class)
     public boolean updateUser(AddUserDTO userDTO) {

          Long userId = userDTO.getId();
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

          existUser.setUserName(userDTO.getUserName())
                  .setNickName(userDTO.getNickName())
                  .setPhone(userDTO.getPhone())
                  .setAvatar(userDTO.getAvatar())
                  .setEmail(userDTO.getEmail());

          return baseMapper.updateById(existUser) > 0;
     }

     @Override
     public boolean changeUserStatus(Long userId, Integer userStatus) {
          boolean exist = EnumUtil.isExist(SysStatusEnum.class, userStatus);
          Assert.isTrue(exist, "用户状态不存在");
          Assert.notNull(getById(userId), "用户不存在");

          return update(null, Wrappers.lambdaUpdate(SysUserDO.class)
                  .eq(SysUserDO::getId, userId)
                  .set(SysUserDO::getUserStatus, userStatus));
     }

     @Override
     public List<ExcelFailResult> importUser(InputStream inputStream) {
          // 处理数据
          Consumer<List<ImportUserDTO>> consumer = rowList -> {
               List<SysUserDO> saveList = new ArrayList<>();
               for (ImportUserDTO userDTO : rowList) {
                    SysUserDO sysUserDO = new SysUserDO();
                    BeanUtil.copyProperties(userDTO, sysUserDO, "sex");

                    // 性别
                    Integer sex = Convert.toInt(EnumUtil.getValueByLabel(UserSexEnum.class, userDTO.getSex()));
                    sysUserDO.setSex(sex);
                    sysUserDO.setPassword(passwordEncoder.encode("123456"));
                    saveList.add(sysUserDO);
               }
               this.saveBatch(saveList);
          };
          return ExcelUtil.importData(inputStream, ImportUserDTO.class, consumer);
     }

     @Override
     public void exportUser(QueryUserDTO queryUserDTO, ServletOutputStream outputStream) {

          List<SysUserListVO> userList = sysUserMapper.queryUserList(queryUserDTO);
          List<ExportUserVO> exportList = userList.stream()
                  .map(user -> {
                       ExportUserVO exportUserVO = new ExportUserVO();
                       BeanUtil.copyProperties(user, exportUserVO);
                       exportUserVO.setUserStatus(EnumUtil.getLabelByValue(SysStatusEnum.class, user.getUserStatus()));
                       return exportUserVO;
                  }).collect(Collectors.toList());
          EasyExcel.write(outputStream, ExportUserVO.class).sheet("用户").doWrite(exportList);
     }

     @Override
     public List<RoleRelateUserVO> listUserByRoleId(Long roleId, String keyword, CommonPage page) {
          PageHelper.startPage(page.getPageNum(), page.getPageSize());
          List<RoleRelateUserVO> vos = sysUserMapper.listUserByRoleId(roleId, keyword);
          page.setTotal(new PageInfo<>(vos).getTotal());
          return vos;
     }
}
