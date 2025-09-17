package com.ev.config;

import com.ev.services.SecondTaskletService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SampleJob {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private SecondTaskletService secondTaskletService;

    @Bean
    public Job myFirstJob() {
        return new JobBuilder("myFirstJob",jobRepository).start(myFirstStep()).next(mySecondStep()).build();
    }

    private Step mySecondStep() {
        return new StepBuilder("mySecondStep",jobRepository).tasklet(secondTaskletService,platformTransactionManager).build();
    }
    private Step myFirstStep() {
        return new StepBuilder("myFirstStep",jobRepository).tasklet(((contribution, chunkContext) -> {System.out
                .println("I am running my first step in the job");
            return RepeatStatus.FINISHED;}),
                platformTransactionManager).build();
    }
}
