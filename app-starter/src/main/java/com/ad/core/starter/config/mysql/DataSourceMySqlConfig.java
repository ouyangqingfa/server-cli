package com.ad.core.starter.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * mysql数据源配置
 *
 * @author CoderYoung
 */
@Configuration
@MapperScan(basePackages = {"com.ad.core.system.mapper", "com.ad.modules.*.mapper"}, sqlSessionFactoryRef = "MySqlSessionFactory")
public class DataSourceMySqlConfig {

    @Resource
    private MySqlParamConfig mySqlParamConfig;

    @Bean(name = "MySqlDataSource")
    @Primary
    public DataSource getMySqlDateSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(mySqlParamConfig.getUrl());
        datasource.setDriverClassName(mySqlParamConfig.getDriverClassName());
        datasource.setInitialSize(mySqlParamConfig.getInitialSize());
        datasource.setMinIdle(mySqlParamConfig.getMinIdle());
        datasource.setMaxActive(mySqlParamConfig.getMaxActive());
        datasource.setMaxWait(mySqlParamConfig.getMaxWait());
        datasource.setUsername(mySqlParamConfig.getUsername());
        datasource.setPassword(mySqlParamConfig.getPassword());
        datasource.setValidationQuery("select 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        return datasource;
    }


    @Bean(name = "MySqlSessionFactory")
    @Primary
    public SqlSessionFactory mySqlSessionFactory(@Qualifier("MySqlDataSource") DataSource datasource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/ad/**/mapper/mapping/*.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        bean.setConfiguration(configuration);

        bean.setGlobalConfig(new GlobalConfig().setBanner(false));
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        bean.setPlugins(interceptor);
        return bean.getObject();
    }


    @Bean("MySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mySqlSessionTemplate(
            @Qualifier("MySqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }

}
