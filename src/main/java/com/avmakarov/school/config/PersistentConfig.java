package com.avmakarov.school.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.jpa.HibernateHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import org.hibernate.FlushMode;
import org.hibernate.cfg.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.hibernate.dialect.PostgreSQLDialect;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PersistentConfig {

    private final Settings settings;

    @Autowired
    public PersistentConfig(Settings settings) {
        this.settings = settings;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setDataSource(dataSource());
        liquibase.setDefaultSchema(settings.getDatabaseSchema());
        return liquibase;
    }

    private static final String MODEL_PATH = "com.avmakarov.school";

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(settings.getDatabaseUrl());
        config.setUsername(settings.getDatabaseUsername());
        config.setPassword(settings.getDatabasePassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        return new HikariDataSource(config);
    }

    public Map<String, Object> getJpaProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Environment.DIALECT, PostgreSQLDialect.class.getName());
        properties.put(Environment.STATEMENT_BATCH_SIZE, 1000);

        properties.put("hibernate.temp.use_jdbc_metadata_defaults", false);
        properties.put("hibernate.proc.param_null_passing", true);
        properties.put(Environment.ORDER_UPDATES, true);
        properties.put(Environment.AUTOCOMMIT, false);
        properties.put(HibernateHints.HINT_FLUSH_MODE, FlushMode.COMMIT);
        properties.put("spring.jpa.hibernate.ddl-auto", "update");
        properties.put("spring.jpa.generate-ddl", false);

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        em.setJpaVendorAdapter(adapter);

        em.setPackagesToScan(MODEL_PATH);

        em.setJpaPropertyMap(getJpaProperties());
        return em;
    }
}
