package com.ev.creationObjectPattern;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class JobFactory {

    @Autowired
    private ApplicationContext applicationContext;
    public Job getJob(String jobName) {
        Job demanedJob = null;
        if("myFirstJob".equals(jobName)) {
            demanedJob = (Job) applicationContext.getBean("myFirstJob");
        }
        else if("mySecondJob".equals(jobName)) {
            demanedJob = (Job) applicationContext.getBean("mySecondJob");
        }
        return demanedJob;
    }
}
