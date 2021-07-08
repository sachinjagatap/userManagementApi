package com.user_management_multidb_api.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.user_management_multidb_api.mysql.entities.User;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "mySqlEntityManagerFactory", 
                       transactionManagerRef = "mySqlTransactionManager", 
                       basePackages = {"com.user_management_multidb_api.mysql" })
public class MysqlConfig {

	@Primary
	@Bean(name = "mySqlDataSourceProperties")
	@ConfigurationProperties("spring.datasource")
	public DataSourceProperties mySqlDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(name = "mySqlDataSource")
	public DataSource mySqlDataSource() {
		return mySqlDataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "mySqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBuilder(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mySqlDataSource()).packages(User.class).build();
	}

	@Primary
	@Bean(name = "mySqlTransactionManager")
	public PlatformTransactionManager mySqlTransactionManager(
			@Qualifier("mySqlEntityManagerFactory") EntityManagerFactory couponEntityManagerFactory) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(couponEntityManagerFactory);
							  jpaTransactionManager.setNestedTransactionAllowed(true);
							  jpaTransactionManager.setRollbackOnCommitFailure(true);
		return jpaTransactionManager;
	}

}
