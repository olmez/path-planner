package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MongoConfig {

  @Value("${spring.data.mongodb.database}") private String database;

  @Value("${spring.data.mongodb.host}") private String host;

  @Value("${spring.data.mongodb.port}") private int port;

  @Value("${spring.data.mongodb.username}") private String username;

  @Value("${spring.data.mongodb.password}") private String password;

  @Bean
  public MongoClientSettings mongoClientSettings() {
    ConnectionString connectionString =
        new ConnectionString("mongodb://" + username + ":" + password + "@" +
                             host + ":" + port + "/" + database);
    return MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build();
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(MongoClients.create(mongoClientSettings()),
                             database);
  }
}
