package org.tact.base;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tact.base.resolver.IpAddressArgumentResolver;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class BaseApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
	
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new IpAddressArgumentResolver());
    }
}
