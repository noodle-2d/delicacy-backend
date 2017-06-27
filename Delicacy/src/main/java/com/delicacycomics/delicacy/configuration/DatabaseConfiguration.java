package com.delicacycomics.delicacy.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                "jdbc:postgresql://localhost:5432/delicacy_database",
                "delicacy_user", "postgres");
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

}