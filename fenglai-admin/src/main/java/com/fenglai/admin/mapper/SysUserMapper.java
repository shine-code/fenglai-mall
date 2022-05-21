package com.fenglai.admin.mapper;

import com.fenglai.admin.pojo.dos.SysUserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fenglai.admin.pojo.dtos.QueryUserDTO;
import com.fenglai.admin.pojo.vos.SysUserListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 用户表 - Mapper接口
 *
 * @author TJ
 * @date: 2022-05-15
 */
public interface SysUserMapper extends BaseMapper<SysUserDO> {

    List<SysUserListVO> queryUserList(@Param("param") QueryUserDTO queryUserDTO);
}
