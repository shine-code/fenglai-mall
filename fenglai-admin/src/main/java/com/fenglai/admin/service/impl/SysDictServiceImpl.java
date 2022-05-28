package com.fenglai.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.fenglai.admin.pojo.dos.SysDictDO;
import com.fenglai.admin.mapper.SysDictMapper;
import com.fenglai.admin.pojo.dtos.DictWithItemDTO;
import com.fenglai.admin.pojo.vos.SysDicListVO;
import com.fenglai.admin.service.ISysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fenglai.common.web.response.CommonPage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

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

     @Override
     public Collection<SysDicListVO> getDictList(String dictKeyword, String itemKeyword, CommonPage page) {

          Map<Long, SysDicListVO> resMap = new HashMap<>(32);
          List<DictWithItemDTO> dictDTOS = sysDictMapper.getDictList(dictKeyword, itemKeyword);
          dictDTOS.forEach(dict -> {

               SysDicListVO vo = resMap.get(dict.getDictId());
               if (vo != null) {

                    vo.getDictItems().add(convertDictItem(dict));
               } else {

                    resMap.put(dict.getDictId(), convertDict(dict));
               }
          });

          return resMap.values();
     }

     private SysDicListVO.DictItem convertDictItem(DictWithItemDTO dict) {
          return new SysDicListVO.DictItem()
                  .setItemId(dict.getItemId())
                  .setItemCode(dict.getItemCode())
                  .setItemName(dict.getItemName())
                  .setRemark(dict.getItemRemark())
                  .setSort(dict.getItemSort())
                  .setStatus(dict.getItemStatus());
     }

     private SysDicListVO convertDict(DictWithItemDTO dict) {
          return new SysDicListVO()
                  .setDictId(dict.getDictId())
                  .setDictCode(dict.getDictCode())
                  .setDictName(dict.getDictName())
                  .setRemark(dict.getDictRemark())
                  .setSort(dict.getDictSort())
                  .setStatus(dict.getDictStatus())
                  .setDictItems(CollUtil.newArrayList(convertDictItem(dict)));
     }
}
