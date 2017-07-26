package com.myshop.shopbackend.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.myshop.shopbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
}
