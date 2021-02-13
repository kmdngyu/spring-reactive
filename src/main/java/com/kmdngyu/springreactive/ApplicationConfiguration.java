package com.kmdngyu.springreactive;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class ApplicationConfiguration extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(H2ConnectionConfiguration.builder()
                .inMemory("test")
                .build());
    }

    @Bean
    ConnectionFactoryInitializer initializer(H2ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema/schema.sql")));
        return initializer;
    }
}
