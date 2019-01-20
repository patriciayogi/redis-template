package com.search.job.indeed.dao;

import com.search.job.indeed.models.response.IndeedSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class IndeedDAO {

    private static final String KEY = "jobs_indeed";

    @Autowired
    private RedisTemplate<String, IndeedSearch> redisTemplate;

    public void addJob(IndeedSearch indeedSearch) {
        redisTemplate.opsForList().leftPush(KEY, indeedSearch);
    }

    public long getNumberOfJobs() {
        return redisTemplate.opsForList().size(KEY);
    }

    public IndeedSearch getJobAtIndex(Integer index) {
        return redisTemplate.opsForList().index(KEY, index);
    }

    public void removeJob(IndeedSearch i) {
        redisTemplate.opsForList().remove(KEY, 1, i);
    }
}
