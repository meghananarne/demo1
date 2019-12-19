package com.example.demo.task;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

import com.example.demo.repository.Student;

public class StudentReader implements ItemReader<Student> {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentReader.class);

	private int nextStudentIndex;
	private List<Student> studentData;

	public StudentReader() {
		initialize();
	}

	private void initialize() {
		Student tony = new Student();
		tony.setName("tony");
		//tony.setPassportNumber("pass1");

		Student nick = new Student();
		nick.setName("nick");
		//nick.setPassportNumber("pass2");

		Student ian = new Student();
		tony.setName("ian");
		//tony.setPassportNumber("pass3");

		studentData = Collections.unmodifiableList(Arrays.asList(tony, nick, ian));
		nextStudentIndex = 0;
	}

	@Override
	public Student read() throws Exception {
		LOGGER.info("Reading the information of the next student");

		Student nextStudent = null;

		if (nextStudentIndex < studentData.size()) {
			nextStudent = studentData.get(nextStudentIndex);
			nextStudentIndex++;
		}

		LOGGER.info("Found student: {}", nextStudent);

		return nextStudent;
	}

}
