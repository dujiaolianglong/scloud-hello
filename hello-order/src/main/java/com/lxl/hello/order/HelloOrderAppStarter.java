/**
 * 
 */
package com.lxl.hello.order;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@RestController
@RequestMapping(value = "/appapi")
@EnableEurekaClient
@RefreshScope
public class HelloOrderAppStarter {

	public static void main(String[] args) {
		SpringApplication.run(HelloOrderAppStarter.class, args);
	}

	@Value("${redis.conn:777}")
	private String redisConn;

	@Value("${kafka.conn:888}")
	private String kafkaConn;

	@Value("${db.conn:999}")
	private String dbConn;

	@Value("${kafka.nodes:123456}")
	private String kafkaNodes;

	@Value("${redis.nodes:789456}")
	private String redisNodes;

	@Value("${my.name:lxl}")
	private String myName;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Map<String, Object> redis() {
		Map<String, Object> map = new HashMap<>();
		map.put("redisConn", redisConn);
		map.put("kafkaConn", kafkaConn);
		map.put("dbConn", dbConn);
		map.put("kafkaNodes", kafkaNodes);
		map.put("redisNodes", redisNodes);
		map.put("myName", myName);
		map.put("date", new Date());
		return map;
	}

	@RequestMapping(value = "/testmap", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> map(@RequestBody Map<String, Object> map) {
		return map;
	}

}
