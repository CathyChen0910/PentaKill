package com.sf.marathon.pentakill.server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServerApplication.class);
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		SpringApplication application = new SpringApplication(ServerApplication.class);
		application.setDefaultProperties(map);
		application.run(args);
	}
}
