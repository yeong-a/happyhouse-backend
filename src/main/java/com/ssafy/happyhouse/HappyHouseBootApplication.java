package com.ssafy.happyhouse;

import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ssafy.happyhouse.listener.InitListener;

@SpringBootApplication
public class HappyHouseBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyHouseBootApplication.class, args);
	}

	@Bean
	ServletListenerRegistrationBean<ServletContextListener> servletListener() {
	    ServletListenerRegistrationBean<ServletContextListener> srb = new ServletListenerRegistrationBean<>();
	    srb.setListener(new InitListener());
	    return srb;
	}
}
