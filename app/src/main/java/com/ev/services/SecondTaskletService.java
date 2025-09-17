package com.ev.services;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

@Service //annotating with this so that we want spring to handle the injection where ever it is autorwired
public class SecondTaskletService implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.println("I am executing a huge service");
        return RepeatStatus.FINISHED;
    }
}
