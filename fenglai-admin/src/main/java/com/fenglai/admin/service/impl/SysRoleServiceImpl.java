package com.fenglai.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fenglai.admin.mapper.SysUserMapper;
import com.fenglai.admin.pojo.dos.SysRoleDO;
import com.fenglai.admin.mapper.SysRoleMapper;
import com.fenglai.admin.pojo.dos.SysUserDO;
import com.fenglai.admin.pojo.dos.SysUserRoleDO;
import com.fenglai.admin.pojo.dtos.AddRoleDTO;
import com.fenglai.admin.pojo.dtos.QueryRoleDTO;
import com.fenglai.admin.pojo.enums.SysStatusEnum;
import com.fenglai.admin.pojo.vos.RoleInfoVO;
import com.fenglai.admin.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fenglai.admin.service.ISysUserRoleService;
import com.fenglai.common.web.response.CommonPage;
import com.fenglai.common.web.utils.EnumUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 角色表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleDO> implements ISysRoleService {

     @Autowired
     private SysRoleMapper sysRoleMapper;
     @Autowired
     private SysUserMapper sysUserMapper;
     @Autowired
     private ISysUserRoleService userRoleService;

     @Override
     public List<RoleInfoVO> queryRoleList(QueryRoleDTO queryRoleDTO, CommonPage page) {

          LambdaQueryWrapper<SysRoleDO> queryWrapper = new LambdaQueryWrapper<>();
          queryWrapper.in(CollUtil.isNotEmpty(queryRoleDTO.getIds()),
                          SysRoleDO::getId, queryRoleDTO.getIds())
                  .ge(queryRoleDTO.getCreateStartTime() != null,
                          SysRoleDO::getCreateTime, queryRoleDTO.getCreateStartTime())
                  .le(queryRoleDTO.getCreateEndTime() != null,
                          SysRoleDO::getCreateTime, queryRoleDTO.getCreateEndTime());

          String keyword = queryRoleDTO.getKeyword();
          queryWrapper.and(StrUtil.isNotBlank(keyword), i -> i.eq(SysRoleDO::getRoleCode, keyword)
                  .or().eq(SysRoleDO::getRoleName, keyword));

          Page<SysRoleDO> doPage = new Page<>(page.getPageNum(), page.getPageSize());
          Page<SysRoleDO> pageList = this.page(doPage, queryWrapper);
          page.setTotal(pageList.getTotal());
          return pageList.getRecords().stream()
                  .map(role -> {
                       RoleInfoVO vo = new RoleInfoVO();
                       BeanUtil.copyProperties(role, vo);
                       vo.setRoleStatus(EnumUtil.getLabelByValue(SysStatusEnum.class, role.getRoleStatus()));
                       return vo;
                  }).collect(Collectors.toList());
     }

     @Override
     public boolean addAndUpdateRole(AddRoleDTO roleDTO) {
          Assert.isTrue(EnumUtil.isExist(SysStatusEnum.class, roleDTO.getRoleStatus()), "角色状态不存在");
          LambdaQueryWrapper<SysRoleDO> queryWrapper = new LambdaQueryWrapper<SysRoleDO>()
                  .eq(SysRoleDO::getRoleName, roleDTO.getRoleName());
          Assert.isNull(this.getOne(queryWrapper, false), "角色名已存在");

          SysRoleDO roleDO = new SysRoleDO();
          BeanUtil.copyProperties(roleDTO, roleDO);
          return this.saveOrUpdate(roleDO);
     }

     @Override
     public boolean changeRoleStatus(Long id, Integer roleStatus) {

          boolean exist = EnumUtil.isExist(SysStatusEnum.class, roleStatus);
          Assert.isTrue(exist, "角色状态不存在");
          Assert.notNull(getById(id), "角色不存在");

          return update(new SysRoleDO(), Wrappers.lambdaUpdate(SysRoleDO.class)
                  .eq(SysRoleDO::getId, id)
                  .set(SysRoleDO::getRoleStatus, roleStatus));
     }

     @Override
     public void exportRoleList(QueryRoleDTO queryRoleDTO, ServletOutputStream outputStream) {

          LambdaQueryWrapper<SysRoleDO> queryWrapper = new LambdaQueryWrapper<>();
          queryWrapper.in(CollUtil.isNotEmpty(queryRoleDTO.getIds()),
                          SysRoleDO::getId, queryRoleDTO.getIds())
                  .ge(queryRoleDTO.getCreateStartTime() != null,
                          SysRoleDO::getCreateTime, queryRoleDTO.getCreateStartTime())
                  .le(queryRoleDTO.getCreateEndTime() != null,
                          SysRoleDO::getCreateTime, queryRoleDTO.getCreateEndTime());

          String keyword = queryRoleDTO.getKeyword();
          queryWrapper.and(StrUtil.isNotBlank(keyword), i -> i.eq(SysRoleDO::getRoleCode, keyword)
                  .or().eq(SysRoleDO::getRoleName, keyword));

          List<SysRoleDO> roleList = this.list(queryWrapper);
          List<RoleInfoVO> exportList = roleList.stream()
                  .map(role -> {
                       RoleInfoVO vo = new RoleInfoVO();
                       BeanUtil.copyProperties(role, vo);
                       vo.setRoleStatus(EnumUtil.getLabelByValue(SysStatusEnum.class, role.getRoleStatus()));
                       return vo;
                  }).collect(Collectors.toList());
          EasyExcel.write(outputStream, RoleInfoVO.class).sheet("角色").doWrite(exportList);
     }

    @Override
    public boolean addRoleRelateUser(Long roleId, List<Long> userIds) {

        LambdaQueryWrapper<SysUserDO> queryWrapper = new LambdaQueryWrapper<SysUserDO>()
                .in(SysUserDO::getId, userIds);
        List<Long> existUserIds = sysUserMapper.selectList(queryWrapper)
                .stream()
                .map(SysUserDO::getId)
                .collect(Collectors.toList());

        Collection<Long> subtract = CollUtil.subtract(userIds, existUserIds);
        Assert.isNull(subtract, "用户id不存在, 请检查!");

        List<SysUserRoleDO> collect = userIds.stream()
                .map(userId -> new SysUserRoleDO()
                        .setUserId(userId)
                        .setRoleId(roleId)).collect(Collectors.toList());
        return userRoleService.saveBatch(collect);
    }
}
