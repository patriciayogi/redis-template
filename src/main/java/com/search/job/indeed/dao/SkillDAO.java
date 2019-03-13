package com.search.job.indeed.dao;

import com.search.job.indeed.models.response.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Objects.nonNull;


@Repository
public class SkillDAO {

    private static final String KEY = "skills";

    @Autowired
    private RedisTemplate<String, Skill> connSkillTemplate;

    public void save(Skill skill) {
        connSkillTemplate.opsForList().leftPush(KEY,skill);
    }

    public void delete(long index) {
        Skill skill = connSkillTemplate.opsForList().index(KEY, index);

        connSkillTemplate.opsForList().remove(KEY,0, skill);
    }


    public void deleteAll() {
        connSkillTemplate.delete(KEY);
    }

    public List<Skill> findAll() {
        List<Skill> results = new ArrayList<>();

        long size = connSkillTemplate.opsForList().size(KEY);
        IntStream.range(0,(int)size-1).forEach(
                (i -> {
                    Skill result = connSkillTemplate.opsForList().index(KEY, i);
                    result.setIndex(String.valueOf(i));
                    if (nonNull(result)){
                        results.add(result);
                    }
                }));
        return results;
    }

}

