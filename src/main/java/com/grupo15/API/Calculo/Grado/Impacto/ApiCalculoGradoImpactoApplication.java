package com.grupo15.API.Calculo.Grado.Impacto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ApiCalculoGradoImpactoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCalculoGradoImpactoApplication.class, args);
	}

}
