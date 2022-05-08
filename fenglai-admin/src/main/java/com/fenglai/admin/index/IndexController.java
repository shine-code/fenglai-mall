package com.fenglai.admin.index;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fenglai.common.web.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-26
 **/
@Slf4j
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired(required = false)
    private JdbcTemplate mysqlJdbc;

    @Autowired
    IndexMapper indexMapper;

    @RequestMapping("/v1")
    public R get() {
//        String sql = "select * from data_test limit 1";
//        List<Map<String, Object>> maps = mysqlJdbc.queryForList(sql);
        log.info("卡死复健科");
        return R.ok();
    }
}
