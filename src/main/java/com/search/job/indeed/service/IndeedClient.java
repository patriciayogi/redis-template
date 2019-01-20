package com.search.job.indeed.service;

import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.IndeedResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class IndeedClient {

    private final RestTemplate restTemplate;

    public IndeedClient(RestTemplate rest) {
        this.restTemplate = rest;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public IndeedResponse readingList() {
        URI uri = URI.create("http://api.indeedSearch.com/ads/apisearch?publisher=5730525017407848&q=java+spring&l=vancover%2C+bc&sort=&format=json&radius=&st=&jt=&start=&limit=50&fromage=20&filter=0&latlong=1&co=ca&chnl=&v=2");
        IndeedSearch indeedSearch = this.restTemplate.getForObject(uri, IndeedSearch.class);

        return IndeedResponse.builder().indeedSearch(indeedSearch).build();
    }

    public IndeedResponse reliable() {
        return IndeedResponse.builder().indeedSearch(new IndeedSearch()).build();
    }

}
