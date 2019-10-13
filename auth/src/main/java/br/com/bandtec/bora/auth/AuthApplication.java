package br.com.bandtec.bora.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfigurationProperties(value = JwtConfiguration.class)
@EntityScan({"br.com.bandtec.core.model"})
@EnableJpaRepositories({"br.com.bandtec.core.repository"})
@EnableEurekaClient
@ComponentScan("br.com.bandtec.bora")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
