package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.Student;
import com.example.demo.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentRepository repository;

	/*@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("accountJob")
	Job accountKeeperJob;
*/
	@GetMapping
	public List<Student> getAllStudents() {
		List<Student> students = repository.findAll();
		System.out.println(students);
		return students;
	}

/*	@RequestMapping("/run-batch-job")
	public String handle() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder().addString("source", "Spring Boot").toJobParameters();
		jobLauncher.run(accountKeeperJob, jobParameters);

		return "Batch job has been invoked";
	}
*/
}
