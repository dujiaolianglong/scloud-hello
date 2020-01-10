/**
 * 
 */
package com.lxl.hello.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipKinAppStarter {
	public static void main(String[] args) {
		SpringApplication.run(ZipKinAppStarter.class, args);
	}
}
