package com.fenglai.admin.service;

import com.fenglai.admin.pojo.dos.SysUserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fenglai.admin.pojo.dtos.AddUserDTO;
import com.fenglai.admin.pojo.dtos.QueryUserDTO;
import com.fenglai.admin.pojo.vos.SysUserListVO;
import com.fenglai.common.core.excel.ExcelFailResult;
import com.fenglai.common.web.response.Page;

import javax.servlet.ServletOutputStream;
import java.io.InputStream;
import java.util.List;

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

    List<SysUserListVO> queryUserList(QueryUserDTO queryUserDTO, Page page);

    List<ExcelFailResult> importUser(InputStream inputStream);

    void exportUser(QueryUserDTO queryUserDTO, ServletOutputStream outputStream);
}
