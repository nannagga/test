package com.java.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


@Configuration
public class AppConfig {
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		//1. datasource를 가져옴.
		sessionFactory.setDataSource(dataSource);
		//2.Mapper.xml정보를 1개씩 가져옴. 여러개 배열로 받음.
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml");
		sessionFactory.setMapperLocations(res);
		return sessionFactory.getObject();
	}//sqlSessionFactory
	
	@Bean
	public SqlSessionTemplate squSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory); //connection
	}
	
}
