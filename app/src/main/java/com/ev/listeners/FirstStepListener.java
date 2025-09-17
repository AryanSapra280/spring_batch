package com.ev.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {
    public void beforeStep(StepExecution stepExecution) {

        System.out.println("Before Executing the step");
    }

    //intercepting the flow
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println("after Executing the step");
        return ExitStatus.FAILED; // this should be COMPLETED, just to show that I can intercept the flow I made it FAILED.
    }
}
