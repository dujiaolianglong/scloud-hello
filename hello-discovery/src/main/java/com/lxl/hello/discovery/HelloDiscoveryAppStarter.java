/**
 * 
 */
package com.lxl.hello.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class HelloDiscoveryAppStarter {
	public static void main(String[] args) {
		SpringApplication.run(HelloDiscoveryAppStarter.class, args);
	}
}
