package com.ssafy.happyhouse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/qna").setViewName("redirect:/qna/");
		registry.addViewController("/qna/").setViewName("forward:/qna/index.html");
	}
}
