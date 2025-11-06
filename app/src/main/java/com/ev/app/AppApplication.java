package com.ev.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({"com.ev.config","com.ev.services", "com.ev.listeners","com.ev.itemReaders","com.ev.itemProcessors"
		,"com.ev.itemWritter", "com.ev.controller", "com.ev.creationObjectPattern"})
//@EnableBatchProcessing //enables batch processing features
@EnableAsync
@EnableScheduling
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
