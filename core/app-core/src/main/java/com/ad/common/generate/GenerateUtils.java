package com.ad.common.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;

/**
 * @author CoderYoung
 */
public class GenerateUtils {

//    public void generateTableCode(String[] tables, String outPath, String basePkg, String mysqlUrl, String mysqlUser, String mysqlPwd) {
//        // 1. 全局配置
//        GlobalConfig config = new GlobalConfig.Builder().build();
//
//        // 是否支持AR模式
//        config.setActiveRecord(true)
//                .setAuthor("CoderYoung")
//                .setOutputDir(outPath)
//                .setFileOverride(false)
//                .setIdType(IdType.ID_WORKER_STR)
//                .setServiceName("%sService")
//                .setServiceImplName("%sServiceImpl")
//                .setMapperName("%sMapper")
//                .setControllerName("%sController")
//                // IEmployeeService
//                .setOpen(false).setBaseResultMap(true)
//                .setBaseColumnList(true);
//
//        // 2. 数据源配置
//        DataSourceConfig dsConfig = new DataSourceConfig();
//        dsConfig.setDbType(DbType.MYSQL)
//                .setDriverName("com.mysql.cj.jdbc.Driver")
//                .setUrl(mysqlUrl)
//                .setUsername(mysqlUser)
//                .setPassword(mysqlPwd);
//
//        // 3. 策略配置globalConfiguration中
//        StrategyConfig stConfig = new StrategyConfig();
//        stConfig.setCapitalMode(true)
////                .setTablePrefix("TB")
//                .setNaming(NamingStrategy.underline_to_camel)
//                .setInclude(tables);
//
//        // 4. 包名策略配置
//        PackageConfig pkConfig = new PackageConfig();
//        pkConfig.setParent(basePkg)
//                .setMapper("mapper")
//                .setService("service")
//                .setServiceImpl("service.impl")
//                .setController("controller")
//                .setEntity("entity")
//                .setXml("mapper.mapping");
//
//        // 自定义模板
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig
////                .setEntity("templates/entity.java")
////                .setController("/templates/myController.java")
//                .setXml(null);
//
//
//        // 5. 整合配置
//        AutoGenerator ag = new AutoGenerator();
//        ag.setGlobalConfig(config)
//                .setDataSource(dsConfig)
//                .setStrategy(stConfig)
//                .setPackageInfo(pkConfig)
//                .setTemplate(templateConfig);
//        // 6. 执行
//        ag.execute();
//    }

    public void generateTableCode(String[] tables, String outPath, String basePkg, String module, String mysqlUrl, String mysqlUser, String mysqlPwd) {
        FastAutoGenerator.create(mysqlUrl, mysqlUser, mysqlPwd)
                .globalConfig(builder -> {
                    builder.author("CoderYoung")
                            .enableSwagger()
                            .fileOverride()
                            .disableOpenDir()
                            .outputDir(outPath);
                }).packageConfig(builder -> {
                    builder.parent(basePkg) // 设置父包名
                            .moduleName(module)
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .entity("entity")
                            .xml("mapper.mapping");
                }).strategyConfig(builder -> {
                    builder.addInclude(tables); // 设置需要生成的表名
                }).templateConfig(config -> {
                    config
//                            .entity("templates/myEntity.java")
                            .controller("templates/myController.java");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

}
