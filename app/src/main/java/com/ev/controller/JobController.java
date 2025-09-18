package com.ev.controller;

import com.ev.creationObjectPattern.JobFactory;
import com.ev.services.JobService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("/")
    public String homePage() {
        return "I am at home page";
    }

    @GetMapping("/start/{jobName}")
    public ResponseEntity<String> startJob(@PathVariable String jobName) throws Exception {
        jobService.startJob(jobName);
        return ResponseEntity.ok("Job is done");
    }
}
