package br.com.postech.service_usuario;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return Mockito.mock(AmazonDynamoDB.class);
    }
}
