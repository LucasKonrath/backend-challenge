package br.com.invillia.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class StoreApplication {

	public static void main(final String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}
