package com.search.job.indeed.service;

import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.IndeedResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class IndeedClient {

    private static final Logger LOGGER = Logger.getLogger(IndeedClient.class.getName());

    private final RestTemplate restTemplate;

    @Value("${indeed.host}")
    private String host;

    @Value("${indeed.publisher}")
    private String publisher;

    @Value("${indeed.query}")
    private String query;

    @Value("${indeed.location}")
    private String location;

    @Value("${indeed.limit}")
    private String limit;

    @Value("${indeed.format}")
    private String format;

    @Value("${indeed.country}")
    private String country;


    public IndeedClient(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public IndeedResponse readingList() {
        // move config to properties
        URI uri = URI.create(getURL());
        IndeedSearch indeedSearch = this.restTemplate.getForObject(uri, IndeedSearch.class);
        // TODO Exception handler
        return IndeedResponse.builder().indeedSearch(indeedSearch).build();
    }

    private String getURL() {
        StringBuilder url = new StringBuilder();
        url.append(host)
           .append("&publisher=").append(publisher)
           .append("&q=").append(query)
           .append("&l=").append(location)
           .append("&format=").append(format)
           .append("&limit=").append(limit)
           .append("&co=").append(country);

        LOGGER.log(Level.INFO, url.toString());
        return url.toString();
    }

    // TODO enhance Response
    public IndeedResponse reliable() {
        return IndeedResponse.builder().indeedSearch(new IndeedSearch()).build();
    }

}
