package com.sf.marathon.pentakill.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServerApplication.class);
	}

	/** Set the ThreadPoolExecutor's core pool size. */
	private final static int corePoolSize = 1;
	/** Set the ThreadPoolExecutor's maximum pool size. */
	private final static int maxPoolSize = 1;
	/** Set the capacity for the ThreadPoolExecutor's BlockingQueue. */
	private final static int queueCapacity = 10;

	@Bean
	public Executor mySimpleAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix("MySimpleExecutor-");
		executor.initialize();
		return executor;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		SpringApplication application = new SpringApplication(ServerApplication.class);
		application.setDefaultProperties(map);
		application.run(args);
	}
}
