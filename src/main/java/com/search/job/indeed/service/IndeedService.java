package com.search.job.indeed.service;

import com.search.job.indeed.dao.IndeedDAO;
import com.search.job.indeed.models.response.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndeedService {

    @Autowired
    private IndeedClient indeedClient;

    @Autowired
    private IndeedDAO indeedDAO;

    public void loadDatabase() {

        // TODO response
        indeedDAO.addAllResults((List) indeedClient.readingList().getIndeedSearch().getResults());
    }


    public void removeAllResults() {
        indeedDAO.removeSearchs();
    }


    public Results removeResults(int index) {
        return indeedDAO.removeResults(index);
    }

    public Results searchResults(int index) {
        return indeedDAO.getResultsAtIndex(index);
    }


    public List<Results> searchResults() {
        return indeedDAO.getResults();
    }



}
