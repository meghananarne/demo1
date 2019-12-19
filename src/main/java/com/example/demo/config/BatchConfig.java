package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.BaseVO;
import com.example.demo.repository.BaseEntity;
import com.example.demo.task.StudentProcessor;
import com.example.demo.task.StudentWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	/*
	 * @Bean ItemReader<Student> studentReader() { return new StudentReader(); }
	 */

	private static final String QUERY_FIND_STUDENTS = "SELECT " + "NAME, " + "PASSPORT " + "FROM STUDENT "
			+ "ORDER BY PASSPORT ASC";

	/*
	 * @Bean ItemReader<Student> studentReader(DataSource dataSource) {
	 * JdbcCursorItemReader<Student> databaseReader = new JdbcCursorItemReader<>();
	 * 
	 * databaseReader.setDataSource(dataSource); databaseReader.setRowMapper(new
	 * BeanPropertyRowMapper<>(Student.class));
	 * databaseReader.setSql(QUERY_FIND_STUDENTS); databaseReader.setFetchSize(2);
	 * 
	 * return databaseReader; }
	 */

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/*
	 * @Bean public JpaPagingItemReader<Student> reader() throws Exception {
	 * JpaPagingItemReader<Student> databaseReader = new JpaPagingItemReader<>();
	 * databaseReader.setEntityManagerFactory(entityManagerFactory);
	 * JpaQueryProviderImpl<Student> jpaQueryProvider = new
	 * JpaQueryProviderImpl<>(); jpaQueryProvider.setQuery("Student.findAll");
	 * databaseReader.setQueryProvider(jpaQueryProvider);
	 * databaseReader.setPageSize(2); databaseReader.afterPropertiesSet(); return
	 * databaseReader; }
	 */

	@Bean(name = "reader")
	@JobScope
	public JpaPagingItemReader<BaseEntity> reader(@Value("#{jobParameters[name]}") String name) throws Exception {
		System.out.println(" Name is :" + name);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", name + "%");
		JpaPagingItemReader<BaseEntity> reader = new JpaPagingItemReader<>();
		reader.setQueryString("SELECT s FROM Student s where s.name like :name");
		reader.setParameterValues(paramMap);
		reader.setPageSize(2);
		reader.setEntityManagerFactory(entityManagerFactory);
		return reader;
	}

	@Bean("reader2")
	@JobScope
	public JpaPagingItemReader<BaseEntity> reader2(@Value("#{jobParameters[name]}") String name) throws Exception {
		System.out.println(" Name is :" + name);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", name + "%");
		JpaPagingItemReader<BaseEntity> reader = new JpaPagingItemReader<>();
		reader.setQueryString("SELECT s FROM Employee s where s.name like :name");
		reader.setParameterValues(paramMap);
		reader.setPageSize(2);
		reader.setEntityManagerFactory(entityManagerFactory);
		return reader;
	}

	@Bean
	ItemProcessor<BaseEntity, BaseVO> studentProcessor() {
		return new StudentProcessor();
	}

	@Bean
	ItemWriter<BaseVO> studentWriter() {
		return new StudentWriter();
	}

	@Bean
	Step step(@Autowired @Qualifier(value = "reader") ItemReader<BaseEntity> studentReader) {
		// .allowStartIfComplete(true)
		return stepBuilderFactory.get("studentStep1").<BaseEntity, BaseVO>chunk(2).reader(studentReader)
				.processor(studentProcessor()).writer(studentWriter()).build();
	}

	@Bean
	Step step2(@Autowired @Qualifier(value = "reader2") ItemReader<BaseEntity> studentReader) {
		// .allowStartIfComplete(true)
		return stepBuilderFactory.get("studentStep2").<BaseEntity, BaseVO>chunk(2).reader(studentReader)
				.processor(studentProcessor()).writer(studentWriter()).build();
	}

	@Bean
	Job job(Step step, @Qualifier(value = "step2") Step step2) {
		return jobBuilderFactory.get("studentJob").incrementer(new RunIdIncrementer()).start(step).next(step2).build();
	}
}