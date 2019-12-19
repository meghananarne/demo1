package com.example.demo.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.example.demo.model.BaseVO;
import com.example.demo.model.StudentVO;
import com.example.demo.repository.BaseEntity;
import com.example.demo.repository.Employee;
import com.example.demo.repository.Student;

public class StudentProcessor implements ItemProcessor<BaseEntity, BaseVO> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentProcessor.class);

	@Override
	public BaseVO process(BaseEntity entity) throws Exception {

		LOGGER.info("Processing entity information: {}", entity);

		if (entity instanceof Student) {
			Student student = (Student) entity;
			return new StudentVO(student.getId(), student.getName(), student.getPassport());
		} else if (entity instanceof Employee) {
			Employee employee = (Employee) entity;
			return new StudentVO(employee.getId(), employee.getName(), employee.getPassport());
		}
		return null;

	}

}
