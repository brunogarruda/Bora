package br.com.bandtec.bora.evento.docs;

import br.com.bandtec.bora.core.docs.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
	public SwaggerConfig() {
		super("br.com.bandtec.bora.evento.controller");
	}

}
