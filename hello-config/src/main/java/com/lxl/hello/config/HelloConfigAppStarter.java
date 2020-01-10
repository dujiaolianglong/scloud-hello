/**
 * 
 */
package com.lxl.hello.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class HelloConfigAppStarter {

	public static void main(String[] args) {
		SpringApplication.run(HelloConfigAppStarter.class, args);
	}
}
