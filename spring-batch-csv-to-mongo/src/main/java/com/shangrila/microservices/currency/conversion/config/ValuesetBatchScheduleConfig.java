package com.shangrila.microservices.currency.conversion.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shangrila.microservices.currency.conversion.model.CotacaoMoeda;
import com.shangrila.microservices.currency.conversion.service.batch.DuplicateRecordSkipListener;

@EnableBatchProcessing
@Configuration
public class ValuesetBatchScheduleConfig extends BatchConfig {

	@Autowired
	private JobBuilderFactory jobs;
	@Autowired
	private StepBuilderFactory steps;
    // tag::jobstep[]
	@Bean(name = "scheduledJob")
	public Job valueSetJob() {
		return jobs.get("scheduledJob")
				.flow(valueSetStep())
				.end()
				.build();
	}

	@Bean
	public Step valueSetStep() {
		return steps.get("scheduledJob")
				.<CotacaoMoeda, CotacaoMoeda>chunk(10)
				.reader(multiResourceItemReader())
				.writer(writer())
				.faultTolerant()
				.skipPolicy(new DuplicateRecordSkipListener())
				.build();
	}
	// end::jobstep[]
}
