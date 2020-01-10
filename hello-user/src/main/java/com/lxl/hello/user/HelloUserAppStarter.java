/**
 * 
 */
package com.lxl.hello.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@RestController
@RequestMapping(value = "/hello")
@EnableEurekaClient
@RefreshScope
public class HelloUserAppStarter {

	private static final Logger logger = LoggerFactory.getLogger(HelloUserAppStarter.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloUserAppStarter.class, args);
	}

	@GetMapping("/trace")
	public String trace(@RequestParam String name) {
		logger.info("I am user service!, name={}", name);
		return "ok, " + name + "!";
	}
}
