package com.evision.dbadapter.controller;

import com.evision.dbadapter.DatabaseConfigResponse;
import com.evision.dbadapter.config.MySQLConfigProperties;
import com.evision.dbadapter.config.PostgreSQLConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.sql.DataSource;

@RestController
@RequestMapping("/config")
public class DatabaseConfigController {

    @Autowired
    MySQLConfigProperties mysqlConfigProperties;

    @Autowired
    PostgreSQLConfigProperties postgresqlConfigProperties;
    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource mysqlDataSource;

    @Autowired
    @Qualifier("postgresqlDataSource")
    private DataSource postgresqlDataSource;

    @GetMapping("/database/{dbName}")
    public DatabaseConfigResponse getDatabaseConfig(@PathVariable String dbName) {
        DatabaseConfigResponse response = new DatabaseConfigResponse();

        if ("mysql".equalsIgnoreCase(dbName)) {
            DataSource dataSource = mysqlDataSource;
            response.setUrl(mysqlConfigProperties.getUrl());
            response.setUsername(mysqlConfigProperties.getUsername());
            response.setPassword(mysqlConfigProperties.getPassword());
        }
        else if ("postgresql".equalsIgnoreCase(dbName)) {
            DataSource dataSource = postgresqlDataSource;
            response.setUrl(postgresqlConfigProperties.getUrl());
            response.setUsername(postgresqlConfigProperties.getUsername());
            response.setPassword(postgresqlConfigProperties.getPassword());
        } else {
            throw new IllegalArgumentException("Unknown database name: " + dbName);
        }

        return response;
    }
}

