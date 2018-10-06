package com.github.loki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Flavio Andrade
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
@PropertySource(value = "file:${LOKI_CONFIGURATIONS}/loki.properties", ignoreResourceNotFound = true)
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
