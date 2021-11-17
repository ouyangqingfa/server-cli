package com.ad.core.starter.config.clickhouse;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * ClickHouse数据源配置
 *
 * @author CoderYoung
 */
//@Configuration
//@MapperScan(basePackages = {"com.ad.modules.test.mapper"}, sqlSessionFactoryRef = "ClickHouseSqlSessionFactory")
public class DataSourceClickHouseConfig {

    @Resource
    private ClickHouseParamConfig clickHouseParamConfig;

    @Bean(name = "ClickHouseDataSource")
    public DataSource getClickHouseDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(clickHouseParamConfig.getUrl());
        datasource.setDriverClassName(clickHouseParamConfig.getDriverClassName());
        datasource.setInitialSize(clickHouseParamConfig.getInitialSize());
        datasource.setMinIdle(clickHouseParamConfig.getMinIdle());
        datasource.setMaxActive(clickHouseParamConfig.getMaxActive());
        datasource.setMaxWait(clickHouseParamConfig.getMaxWait());
        datasource.setUsername(clickHouseParamConfig.getUsername());
        datasource.setPassword(clickHouseParamConfig.getPassword());
        datasource.setValidationQuery("select 1");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        return datasource;
//        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ClickHouseSqlSessionFactory")
    public SqlSessionFactory clickhouseSqlSessionFactory(@Qualifier("ClickHouseDataSource") DataSource datasource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/ad/**/mapper/mapping/*.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setUseDeprecatedExecutor(false);
        bean.setConfiguration(configuration);

        bean.setGlobalConfig(new GlobalConfig().setBanner(false));

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.CLICK_HOUSE));
        bean.setPlugins(interceptor);
        return bean.getObject();
    }

    @Bean("ClickHouseSqlSessionTemplate")
    public SqlSessionTemplate clickhouseSqlSessionTemplate(@Qualifier("ClickHouseSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}
