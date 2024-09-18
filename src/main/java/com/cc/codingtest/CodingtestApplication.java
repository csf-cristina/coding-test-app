package com.cc.codingtest;

import com.cc.codingtest.service.RouterLocationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodingtestApplication implements CommandLineRunner {

	private final RouterLocationService routerLocationService;

	public CodingtestApplication(RouterLocationService routerLocationService) {
		this.routerLocationService = routerLocationService;
	}

	public static void main(String[] args) {
		SpringApplication.run(CodingtestApplication.class, args);
	}

	@Override
	public void run(String... args) {
		routerLocationService.processRouterLocation();
	}
}
