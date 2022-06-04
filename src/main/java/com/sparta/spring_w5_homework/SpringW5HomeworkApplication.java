package com.sparta.spring_w5_homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringW5HomeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringW5HomeworkApplication.class, args);
	}

}
