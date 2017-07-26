package com.myshop.shopbackend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.myshop.shopbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {

    private static final String DATABASE_URL = "jdbc:h2:tcp://localhost/~/myshop";

    private static final String DATABASE_DRIVER = "org.h2.Driver";

    private static final String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";

    private static final String DATABASE_USERNAME = "sa";

    private static final String DATABASE_PASSWORD = "";
}
