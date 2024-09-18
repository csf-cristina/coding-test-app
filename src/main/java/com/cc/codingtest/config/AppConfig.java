package com.cc.codingtest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ConfigurationProperties(prefix="codingtest")
public class AppConfig {

    private String routerLocationUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    public String getRouterLocationUrl() {
        return routerLocationUrl;
    }

    public void setRouterLocationUrl(String routerLocationUrl) {
        this.routerLocationUrl = routerLocationUrl;
    }
}
