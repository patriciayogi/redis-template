package com.search.job.indeed.dao;

import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

@Repository
@Transactional
public class IndeedResultsDAO {

    private static final String KEY = "jobs_results2";

    @Autowired
    private RedisTemplate<String, Object> redisGenericTemplate;

    private HashOperations hashOperations;

    @PostConstruct
    private void init(){
        hashOperations = redisGenericTemplate.opsForHash();
    }

    public void add(final Results results) {
        hashOperations.put(KEY, results.getJobkey(), results);
    }
    public void delete(final String id) {
        hashOperations.delete(KEY, id);
    }
    public Results findResult(final String id){
        return (Results) hashOperations.get(KEY, id);
    }
    public Map<Object, Object> findAllResults(){
        return hashOperations.entries(KEY);
    }

}
