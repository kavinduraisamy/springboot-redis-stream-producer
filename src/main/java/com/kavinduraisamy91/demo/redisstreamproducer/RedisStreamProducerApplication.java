package com.kavinduraisamy91.demo.redisstreamproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedisStreamProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisStreamProducerApplication.class, args);
	}

}
