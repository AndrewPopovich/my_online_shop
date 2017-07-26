package com.myshop.shopbackend.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.myshop.shopbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {

    private static final String DATABASE_URL = "jdbc:h2:tcp://localhost/~/myshop";

    private static final String DATABASE_DRIVER = "org.h2.Driver";

    private static final String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";

    private static final String DATABASE_USERNAME = "sa";

    private static final String DATABASE_PASSWORD = "";

    @Bean
    private DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(DATABASE_URL);
        dataSource.setDriverClassName(DATABASE_DRIVER);
        dataSource.setUsername(DATABASE_USERNAME);
        dataSource.setPassword(DATABASE_PASSWORD);

        return dataSource;
    }
}
