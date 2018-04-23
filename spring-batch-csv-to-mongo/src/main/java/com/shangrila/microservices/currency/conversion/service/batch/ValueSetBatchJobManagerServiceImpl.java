package com.shangrila.microservices.currency.conversion.service.batch;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("valueSetJobManager")
public class ValueSetBatchJobManagerServiceImpl implements BatchJobManagerService {

    @Autowired
    @Resource(name="valueSetJob")
    private Job job;

	@Autowired
	private JobLauncher jobLauncher;

    @Override
    public void batchUpload(String uploadRequestDto) throws Exception {

        try {
            final JobParameters jobParameters =
                    new JobParametersBuilder()
                            .addLong("time",System.currentTimeMillis())
                            .addString("valueSetCsvdata", uploadRequestDto)
                            .toJobParameters();
            
            System.out.println("ValueSet Upload - Started" + jobParameters);
            final JobExecution execution = jobLauncher.run(job, jobParameters);

            System.out.println("ValueSet Upload - Complete  Job Status: " + execution.getStatus());
        }  catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException |
                JobParametersInvalidException | JobRestartException e) {
        	System.out.println("Failed to do Valueset Upload Job Status:" + e.getMessage());

        }

    }

}
