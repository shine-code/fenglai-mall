package com.fenglai.test;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-04-30
 **/
@Data
@Accessors(chain = true)
public class User {

    private Integer id;
    private String name;
    private List<String> job;
}
