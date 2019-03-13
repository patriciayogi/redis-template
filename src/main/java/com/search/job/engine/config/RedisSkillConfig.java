package com.search.job.engine.config;

import com.search.job.engine.service.MessagePublisher;
import com.search.job.engine.service.MessagePublisherImpl;
import com.search.job.engine.service.MessageSubscriber;
import com.search.job.indeed.dao.SkillDAO;
import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.Results;
import com.search.job.indeed.models.response.Skill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;

@EnableRedisRepositories(basePackageClasses = {SkillDAO.class}, redisTemplateRef = "skillRedisTemplate")
@Configuration
public class RedisSkillConfig {



    @Bean(name = "skillFactory")
    public RedisConnectionFactory connectionFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setUsePool(true);
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
        return connectionFactory;
    }


    @Bean(name = "skillRedisTemplate")
    public RedisTemplate<String, Skill> connSkillTemplate() {
        RedisTemplate<String, Skill> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

}
