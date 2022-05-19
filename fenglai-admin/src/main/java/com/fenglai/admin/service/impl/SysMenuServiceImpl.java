package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysMenuDO;
import com.fenglai.admin.mapper.SysMenuMapper;
import com.fenglai.admin.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 菜单表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuDO> implements ISysMenuService {

     @Autowired
     private SysMenuMapper sysMenuMapper;
}
