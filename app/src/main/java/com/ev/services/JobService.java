package com.ev.services;

import com.ev.creationObjectPattern.JobFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobFactory jobFactory;

    @Async
    public void startJob(String jobName) throws Exception {
        Job jobDeemanded = jobFactory.getJob(jobName);

        //creation of jobParameters
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("currentTime",System.currentTimeMillis()).toJobParameters();

        //launch the job
        JobExecution jobExecution = jobLauncher.run(jobDeemanded,jobParameters);
        System.out.println("Job Execution:" + jobExecution);

    }
}
