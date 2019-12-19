package com.example.demo.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import com.example.demo.model.BaseVO;

public class StudentWriter implements ItemWriter<BaseVO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentWriter.class);
	
	@Value("${topic.json}")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, BaseVO> kafkaTemplate;

	@Override
	public void write(List<? extends BaseVO> items) throws Exception {
		LOGGER.info("Received the information of {} students", items.size());
		
		items.forEach(i -> LOGGER.info("Received the information of a student: {}", i));
		
		items.forEach(item -> kafkaTemplate.send(jsonTopic, item));			
	}

}
