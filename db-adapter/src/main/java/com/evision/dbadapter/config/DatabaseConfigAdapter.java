package com.evision.dbadapter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfigAdapter {

    @Autowired
    MySQLConfigProperties mysqlConfigProperties;

    @Autowired
    PostgreSQLConfigProperties postgresqlConfigProperties;

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    @Primary
    @Qualifier("mysqlDataSource")
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(mysqlConfigProperties.getUrl());
        dataSource.setUsername(mysqlConfigProperties.getUsername());
        dataSource.setPassword(mysqlConfigProperties.getPassword());
        return dataSource;
    }

    @Bean
    @Qualifier("postgresqlDataSource")
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(postgresqlConfigProperties.getUrl());
        dataSource.setUsername(postgresqlConfigProperties.getUsername());
        dataSource.setPassword(postgresqlConfigProperties.getPassword());
        return dataSource;
    }

//    @Bean
//    public DataSourceProperties dataSourceProperties() {
//        if ("mysql".equalsIgnoreCase(dataSourceProperties.getName())) {
//            return mysqlConfigProperties;
//        } else if ("postgresql".equalsIgnoreCase(dataSourceProperties.getName())) {
//            return postgresqlConfigProperties;
//        } else {
//            throw new IllegalArgumentException("Unknown database name: " + dataSourceProperties.getName());
//        }
//    }
}
