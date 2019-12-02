package com.example.img.upload.demoigmupload;

import com.example.img.upload.demoigmupload.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ FileStorageProperties.class })
public class DemoDeDownloadEEnvioDeImgDaBaseDeDadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDeDownloadEEnvioDeImgDaBaseDeDadosApplication.class, args);
    }

}
