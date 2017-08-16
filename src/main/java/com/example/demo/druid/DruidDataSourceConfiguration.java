package com.example.demo.druid;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.example.demo.dao")
public class DruidDataSourceConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druidDataSource(){
		DataSource dataSource = new DruidDataSource();
		return dataSource;
	}

	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
		//下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
		fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
		fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
		return fb.getObject();
	}

}
