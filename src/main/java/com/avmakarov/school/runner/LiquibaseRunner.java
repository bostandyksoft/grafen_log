package com.avmakarov.school.runner;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class LiquibaseRunner implements ApplicationRunner {

    private final SpringLiquibase springLiquibase;

    @Autowired
    public LiquibaseRunner(SpringLiquibase springLiquibase) {
        this.springLiquibase = springLiquibase;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        springLiquibase.setShouldRun(true);
        springLiquibase.afterPropertiesSet();
    }

}
