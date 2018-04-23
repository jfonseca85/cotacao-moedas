package com.shangrila.microservices.currency.conversion.config;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shangrila.microservices.currency.conversion.model.Configuracao;

@Component
@EnableBatchProcessing
public class RunScheduler {
	
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Resource(name="scheduledJob")
	private Job job;
	@Autowired
	private Environment environment;
	
	@Autowired
	private Configuracao configuration;

	@Scheduled(cron = "${cron.job.expression}")
	public void run() {
		try {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("time", System.currentTimeMillis())
					.addLong("valueSetCsvdataMultiResource", Long.parseLong(retrieveConfiguration().getDiasDeCache()+""))
					.toJobParameters();
			
			System.out.println("CsvToMongoJob Upload - Started" + jobParameters);
			JobExecution execution = jobLauncher.run(job, jobParameters);

			System.out.println("Exit status : " + execution.getStatus());
		} catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | JobRestartException e) {
        	System.out.println("Failed to do CsvToMongoJob Upload Job Status:" + e.getMessage());

        }
	}
	
	private LimitConfiguration retrieveConfiguration() {
		if(configuration.getDiasDeCache() == 0) return new LimitConfiguration(Integer.parseInt(environment.getProperty("local.diasCache")));
		 return new LimitConfiguration(configuration.getDiasDeCache());
	}
}
