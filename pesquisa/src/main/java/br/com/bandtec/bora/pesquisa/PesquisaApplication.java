package br.com.bandtec.bora.pesquisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories(value = "br.com.bandtec.bora.core.repository")
@ComponentScan(value = "br.com.bandtec.bora")
public class PesquisaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PesquisaApplication.class, args);
    }

}
