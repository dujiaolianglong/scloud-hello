package com.lxl.hello.order.init;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lxl.common.component.base.utils.EnvProfileUtils;
import com.lxl.common.component.kafka.producer.KafkaSendUtils;
import com.lxl.common.component.redis.RedisUtils;

/**
 * 启动支撑类
 * 
 * @author Administrator
 *
 */
@Component
public class Init implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(EnvProfileUtils.class);

	@Autowired
	private RedisUtils redisUtils;

	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private KafkaSendUtils kafkaSendUtils;

	@Override
	public void run(String... args) throws Exception {
		logger.info("env : {}", EnvProfileUtils.getDevProfile());

		RLock rLock = redissonClient.getLock("testlock");
		try {
			rLock.tryLock(500000L, TimeUnit.SECONDS);
			System.out.println();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			rLock.unlock();
		}

		String name = (String) redisUtils.get("name");
		System.out.println(name);

		TestKafka testKafka = new TestKafka();
		testKafka.setId(1);
		testKafka.setName("lxl");
		testKafka.setCreateTime(new Date());
		kafkaSendUtils.send("lxl.test", testKafka);
	}

}

class TestKafka {
	private int id;

	private Date createTime;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
