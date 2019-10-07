package br.com.bandtec.bora.bora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.bandtec.bora.controller"})
@ComponentScan(basePackages = {"br.com.bandtec.bora.model.service"})
@EnableJpaRepositories(basePackages = {"br.com.bandtec.bora.repository"})
@EntityScan(basePackages = {"br.com.bandtec.bora.model.entity"})
@EnableSwagger2
public class BoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoraApplication.class, args);
	}

}
