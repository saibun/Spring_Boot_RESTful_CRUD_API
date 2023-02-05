package com.Rest_Example.CourseList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CourseListApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseListApplication.class, args);
	}
	@Lazy
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
