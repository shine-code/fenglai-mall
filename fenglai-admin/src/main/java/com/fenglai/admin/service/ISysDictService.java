package com.fenglai.admin.service;

import com.fenglai.admin.pojo.dos.SysDictDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fenglai.admin.pojo.vos.SysDicListVO;
import com.fenglai.common.web.response.CommonPage;

import java.util.Collection;

/**
 * @description: 字典表 - Service服务
 *
 * @author TJ
 * @date: 2022-05-15
 */
public interface ISysDictService extends IService<SysDictDO> {

    Collection<SysDicListVO> getDictList(String dictKeyword, String itemKeyword, CommonPage page);
}
