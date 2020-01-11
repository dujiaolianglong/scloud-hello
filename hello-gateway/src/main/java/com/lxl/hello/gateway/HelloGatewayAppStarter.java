/**
 * 
 */
package com.lxl.hello.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.lxl.hello.gateway.filter.PreAccessLogFilter;
import com.lxl.hello.gateway.filter.PreCheckSignFilter;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class HelloGatewayAppStarter {
	public static void main(String[] args) {
		SpringApplication.run(HelloGatewayAppStarter.class, args);
	}

	@Bean
	public PreAccessLogFilter preAccessLogFilter() {
		return new PreAccessLogFilter();
	}

	@Bean
	public PreCheckSignFilter preCheckSignFilter() {
		return new PreCheckSignFilter();
	}
}
