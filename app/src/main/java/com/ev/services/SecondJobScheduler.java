package com.ev.services;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SecondJobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("mySecondJob")
    @Autowired
    private Job mySecondJob;

    @Scheduled(cron = "0 * * * * *")
    public String secondJobScheduler() throws Exception{

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("currentTime",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(mySecondJob,jobParameters);
        return "Job Scheduler worked";
    }
}
