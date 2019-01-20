package com.search.job.indeed.dao;

import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;

@Repository
@Transactional
public class IndeedDAO {

    private static final String KEY = "jobs_indeed";
    //private static final String KEY_RESULTS = "jobs_results";
    private static final String KEY_RESULTS = "jobs_results1";

    @Autowired
    private RedisTemplate<String, Results> redisTemplate;

    @Autowired
    private RedisTemplate<String, IndeedSearch> connIndeedSearch;


    public void removeSearchs() {
        long size = connIndeedSearch.opsForList().size(KEY);

        IntStream.range(0,(int)size-1).forEach(
                (i) -> connIndeedSearch.opsForList().leftPop(KEY) );
    }

    public void addAllResults(List<Results> results) {
        redisTemplate.opsForList().leftPushAll(KEY_RESULTS, results);
    }

/*

    public void addResults(Results results) {
        redisTemplate.opsForList().leftPush(KEY_RESULTS, results);
    }
*/


    public Results getResultsAtIndex(Integer index) {
        return redisTemplate.opsForList().index(KEY_RESULTS, index);
    }

    public List<Results> getResults() {
        List<Results> results = new ArrayList<>();

        long size = connIndeedSearch.opsForList().size(KEY);
        IntStream.range(0,(int)size-1).forEach(
                (i -> {
                    Results result = redisTemplate.opsForList().index(KEY_RESULTS, i);
                    if (nonNull(result)){
                        results.add(result);
                    }
                }));
        return results;
    }


    public long getNumberOfResults() {
        return redisTemplate.opsForList().size(KEY_RESULTS);
    }

    public Results removeResults(Integer index) {
        Results result = redisTemplate.opsForList().index(KEY_RESULTS, index);
        redisTemplate.opsForList().remove(KEY_RESULTS, 1, result);
        return result;
    }
}
