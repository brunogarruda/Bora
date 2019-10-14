package br.com.bandtec.bora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import br.com.bandtec.bora.core.property.JwtConfiguration;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.bandtec.bora.core.model" })
@EnableJpaRepositories(basePackages = { "br.com.bandtec.bora.core.repository" })
@EnableConfigurationProperties(value = JwtConfiguration.class)
@ComponentScan(basePackages = { "br.com.bandtec.bora" })
public class BoraApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoraApplication.class, args);
	}

}
