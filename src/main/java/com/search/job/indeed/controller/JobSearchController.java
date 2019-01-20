package com.search.job.indeed.controller;

import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.service.IndeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@EnableCircuitBreaker
@RestController
public class JobSearchController {

    @Autowired
    private IndeedService indeedService;

    @RequestMapping("/jobs")
    public IndeedSearch index() {

        return indeedService.searchJobs();
    }

}
