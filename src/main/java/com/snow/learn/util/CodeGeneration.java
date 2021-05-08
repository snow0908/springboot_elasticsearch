package com.snow.learn.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author: lx
 * @Description: mybatis-plus代码生成器
 * @Version: 1.0
 */
public class CodeGeneration {

    public static void main(String[] args) {
        // 1. 全局配置
        GlobalConfig config = new GlobalConfig();
        String courseFile = System.getProperty("user.dir");

        // 是否支持AR模式
        config.setActiveRecord(true)
                .setAuthor("lx")
                .setOutputDir(courseFile + "/src/main/java") // 生成路径
                .setFileOverride(false) // 文件覆盖
                .setIdType(IdType.ID_WORKER_STR) // 主键策略
                .setServiceName("%sService") // 设置生成的service接口的名字的首字母是否为I
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setControllerName("%sController")
                // IEmployeeService
                .setOpen(false).setBaseResultMap(true)// 生成基本的resultMap
                .setBaseColumnList(true);// 生成基本的SQL片段

        // 2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL) // 设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC")
                .setUsername("root")
                .setPassword("123456");

        // 3. 策略配置globalConfiguration中
        String[] Include = { "tb_sales"};// 生成的表
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
//    					.setDbColumnUnderline(true) // 指定表名 字段名是否使用下划线
                .setTablePrefix("TB")
//                .entityTableFieldAnnotationEnable(true)
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setInclude(Include);

        // 4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.snow.learn")
                .setMapper("dao.sales")// dao
                .setService("service.sales")// servcie
                .setServiceImpl("service.impl.sales")
                .setController("controller.sales")// controller
                .setEntity("entity.sales")
                .setXml("dao.mapping.sales");// mapper.xml

        // 5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig).setPackageInfo(pkConfig);
        // 6. 执行
        ag.execute();
    }
}
