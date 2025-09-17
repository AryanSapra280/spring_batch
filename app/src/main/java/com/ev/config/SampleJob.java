package com.ev.config;

import com.ev.itemProcessors.FirstItemProcessor;
import com.ev.itemReaders.FirstItemReader;
import com.ev.itemWritter.FirstItemWriter;
import com.ev.listeners.FirstJobListener;
import com.ev.listeners.FirstStepListener;
import com.ev.services.SecondTaskletService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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

    @Autowired
    private FirstJobListener firstJobListener;

    @Autowired
    private FirstStepListener firstStepListener;

    @Autowired
    private FirstItemReader firstItemReader;

    @Autowired
    private FirstItemProcessor firstItemProcessor;

    @Autowired
    private FirstItemWriter firstItemWriter;

//    @Bean comment this because I started working on another job which runs chunk oriented steps
    public Job myFirstJob() {
        return new JobBuilder("myFirstJob",jobRepository).incrementer(new RunIdIncrementer())
                 .start(myFirstStep()).next(mySecondStep()).listener(firstJobListener).build();
    }

    private Step mySecondStep() {
        return new StepBuilder("mySecondStep",jobRepository).tasklet(secondTaskletService,platformTransactionManager)
                .listener(firstStepListener).build();
    }
    private Step myFirstStep() {
        return new StepBuilder("myFirstStep",jobRepository).tasklet(((contribution, chunkContext) -> {System.out
                .println("I am running my first step in the job");
            return RepeatStatus.FINISHED;}),
                platformTransactionManager).build();
    }

    @Bean
    public Job mySecondJob() {
        return new JobBuilder("MySeconJob",jobRepository).incrementer(new RunIdIncrementer()).start(myFirstChunkStep()).build();
    }

    private Step myFirstChunkStep() {
        return new StepBuilder("firstChunkStep", jobRepository)
                .<Integer,Long>chunk(2,platformTransactionManager) //<Integer,Long> are the input to Reader processor and output to writer.
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
