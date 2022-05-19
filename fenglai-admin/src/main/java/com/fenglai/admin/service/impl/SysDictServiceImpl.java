package com.fenglai.admin.service.impl;

import com.fenglai.admin.pojo.dos.SysDictDO;
import com.fenglai.admin.mapper.SysDictMapper;
import com.fenglai.admin.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description: 字典表 - Service服务实现类
 *
 * @author TJ
 * @date: 2022-05-15
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictDO> implements ISysDictService {

     @Autowired
     private SysDictMapper sysDictMapper;
}
