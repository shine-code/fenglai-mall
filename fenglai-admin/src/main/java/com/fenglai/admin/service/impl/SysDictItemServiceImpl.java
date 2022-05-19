package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysDictItemDO;
import com.fenglai.admin.mapper.SysDictItemMapper;
import com.fenglai.admin.service.ISysDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 字典数据表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItemDO> implements ISysDictItemService {

     @Autowired
     private SysDictItemMapper sysDictItemMapper;
}
