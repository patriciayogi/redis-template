package com.search.job.indeed.service;

import com.search.job.indeed.dao.IndeedDAO;
import com.search.job.indeed.models.response.IndeedSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndeedService {

    @Autowired
    private IndeedClient indeedClient;

    @Autowired
    private IndeedDAO indeedDAO;

    public IndeedSearch searchJobs() {

        indeedClient.readingList().getIndeedSearch().getResults();

        return indeedClient.readingList().getIndeedSearch();
    }

}
