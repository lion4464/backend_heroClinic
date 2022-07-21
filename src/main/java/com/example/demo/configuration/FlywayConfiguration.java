package com.example.demo.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration {

    @Autowired
    public FlywayConfiguration(DataSource dataSource) {
        Flyway.configure()
                .baselineOnMigrate(true)
                .baselineVersion("1")
                .sqlMigrationPrefix("V")
                .baselineDescription("true")
                .outOfOrder(true)
                .placeholderSuffix(".sql").dataSource(dataSource).load().migrate();
    }
}
