/**
 * 
 */
package com.lxl.hello.order.init;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.lxl.common.component.kafka.model.KafkaMessage;

/**
 * @author Administrator
 *
 */
@Component
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = { "lxl.test" })
	public void handle(ConsumerRecord<?, ?> record, Acknowledgment ack) {

		long beginTime = System.currentTimeMillis();
		try {
			String value = (String) record.value();
			KafkaMessage message = JSON.parseObject(value, KafkaMessage.class);

			// 消息体业务数据
//			Object data = message.getData();

			logger.info("consumer value={}", message);
			ack.acknowledge();
		} catch (Exception e) {
			logger.error("consumer error", e);
		} finally {
			logger.info("########处理完消息，耗时" + (System.currentTimeMillis() - beginTime) + "ms ######");
		}
	}

}
