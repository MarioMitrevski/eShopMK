package eshop.mk;

import eshop.mk.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class EShopMkApplication {

    public static void main(String[] args) {
        SpringApplication.run(EShopMkApplication.class, args);
    }
}

