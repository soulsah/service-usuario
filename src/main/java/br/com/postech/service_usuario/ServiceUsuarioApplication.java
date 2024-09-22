package br.com.postech.service_usuario;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories(basePackages = "br.com.postech.service_usuario.adapters.out")
public class ServiceUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceUsuarioApplication.class, args);
	}

}
