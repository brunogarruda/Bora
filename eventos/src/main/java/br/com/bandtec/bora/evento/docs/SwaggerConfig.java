package br.com.bandtec.bora.evento.docs;

import org.springframework.context.annotation.Configuration;

import br.com.bandtec.bora.core.docs.BaseSwaggerConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
	public SwaggerConfig() {
		super("br.com.bandtec.bora.controller");
	}

}
