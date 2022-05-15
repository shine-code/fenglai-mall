package com.fenglai.generator;

import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;

/**
 * @description:
 * 
 * @author: TJ
 * @date:  2022-05-12
 **/
public class GenMain {

    private static final String db_url = "*";
    private static final String db_user = "*";
    private static final String db_pwd = "*";

    // 模块名
    private static final String MODULE_NAME = "fenglai-admin";
    // 父包名
    private static final String ROOT_PACKAGE = "com.fenglai.admin";

    public static void main(String[] args) {
        // 数据源设置
        DataSourceConfig.Builder dataSource = new DataSourceConfig.Builder(db_url, db_user, db_pwd);
//                .typeConvert(new MySqlTypeConvert() {
//
//                    @Override
//                    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
//                        if (fieldType.contains("tinyint")) {
//                            return DbColumnType.INTEGER;
//                        }
//                        return super.processTypeConvert(config, fieldType);
//                    }
//                });
        FastAutoGenerator.create(dataSource)
                .globalConfig(builder -> {
                    builder.author("TJ") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()
                            .outputDir(System.getProperty("user.dir") + File.separator + MODULE_NAME + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(ROOT_PACKAGE) // 设置父包名
                            .entity("pojo.dos")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
                                    System.getProperty("user.dir") + File.separator + MODULE_NAME + "\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.likeTable(new LikeTable("sys_", SqlLike.RIGHT)) // 设置需要生成的表名
                            .controllerBuilder()
                                .enableRestStyle()
                            .entityBuilder()
                                .addIgnoreColumns("id","create_time","update_time","create_by","update_by")
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                .convertFileName(entityName -> entityName + "DO")
                            .mapperBuilder()
                                .enableBaseColumnList()
                                .enableBaseResultMap();
                })
                .templateConfig(builder -> {
                    builder.entity("templates/entity.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .mapper("templates/mapper.java")
                            .controller("templates/controller.java")
                            .build();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
