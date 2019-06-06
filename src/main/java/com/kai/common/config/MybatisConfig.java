package com.kai.common.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.kai.common.datasource.DatabaseType;
import com.kai.common.datasource.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.*;

/**
 * @author ggk
 * @date 2019/5/28 0028 上午 9:18
 */
@Profile("dev")
@Configuration
@MapperScan("com.kai.mapper")
public class MybatisConfig {

    @Autowired
    private Environment env;
    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor getPaginationInterceptor(){
        PaginationInterceptor paginationInterceptor=new PaginationInterceptor();
        /*List<ISqlParser> list=new ArrayList<>();
        list.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(list);*/
        return paginationInterceptor;
    }
    @Bean
    public OptimisticLockerInterceptor getOptimisiticLock(){
        return new OptimisticLockerInterceptor();
    }
    @Bean
    public DataSource myTestDataSource() throws Exception{
        Properties ps=new Properties();
        ps.put("driverClassName",env.getProperty("jdbc.driverClassName"));
        ps.put("url",env.getProperty("jdbc.url"));
        ps.put("username",env.getProperty("jdbc.username"));
        ps.put("password",env.getProperty("jdbc.password"));
        return DruidDataSourceFactory.createDataSource(ps);
    }

    @Bean
    public DataSource myTest2DataSource() throws Exception{
        Properties ps=new Properties();
        ps.put("driverClassName",env.getProperty("jdbc2.driverClassName"));
        ps.put("url",env.getProperty("jdbc2.url"));
        ps.put("username",env.getProperty("jdbc2.username"));
        ps.put("password",env.getProperty("jdbc2.password"));
        return DruidDataSourceFactory.createDataSource(ps);
    }
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("myTestDataSource") DataSource dataSource1,
                                        @Qualifier("myTest2DataSource") DataSource dataSource2){
        Map<Object,Object> target=new HashMap<>();
        target.put(DatabaseType.onetest,dataSource1);
        target.put(DatabaseType.twotest,dataSource2);

        DynamicDataSource dataSource=new DynamicDataSource();
        dataSource.setTargetDataSources(target);
        dataSource.setDefaultTargetDataSource(dataSource1);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dbs) throws  Exception{
        MybatisSqlSessionFactoryBean msf=new MybatisSqlSessionFactoryBean();
        msf.setDataSource(dbs);
        Interceptor [] interceptors={getPaginationInterceptor(),getOptimisiticLock()};
        msf.setPlugins(interceptors);
        return msf.getObject();
    }
    @Bean
    public DataSourceTransactionManager  dataSourceTransactionManager(DynamicDataSource dbs){
        return new DataSourceTransactionManager(dbs);
    }


}
