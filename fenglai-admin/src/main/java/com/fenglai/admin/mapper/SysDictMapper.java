package com.fenglai.admin.mapper;

import com.fenglai.admin.pojo.dos.SysDictDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fenglai.admin.pojo.dtos.DictWithItemDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 字典表 - Mapper接口
 *
 * @author TJ
 * @date: 2022-05-15
 */
public interface SysDictMapper extends BaseMapper<SysDictDO> {

    List<DictWithItemDTO> getDictList(@Param("dictKeyword") String dictKeyword, @Param("itemKeyword") String itemKeyword);
}
