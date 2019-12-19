package com.example.demo.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StudentJobLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentJobLauncher.class);

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    //@Scheduled(cron = "${in.memory.reader.job.cron}")
    public void launchJob() throws Exception {
        LOGGER.info("Starting student job");

        jobLauncher.run(job, newExecution());

        LOGGER.info("Stopping student job");
    }

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        //Place holder , We can load dates from Mongo here
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        parameters.put("name", new JobParameter("Bha"));

        return new JobParameters(parameters);
    }
}
