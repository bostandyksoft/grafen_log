package com.avmakarov.school.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class Settings {

    @Value("log.database.url:jdbc:postgresql://host.docker.internal:5432/postgres?currentSchema=log")
    private String databaseUrl;
    @Value("log.database.user:postgres")
    private String databaseUsername;
    @Value("log.database.password:postgres")
    private String databasePassword;
    private String databaseSchema;

    @PostConstruct
    public void init() {
        resolveDatabase();
    }

    private void resolveDatabase() {
        try {
            try(Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword)) {
                databaseSchema =connection.getSchema();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public String getDatabaseSchema() {
        return databaseSchema;
    }
}
