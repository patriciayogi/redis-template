package com.search.job.engine.config;

import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.Results;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    //TODO https://www.javacodegeeks.com/2017/11/intro-redis-spring-boot.html
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
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

    @Bean
    public RedisTemplate<String, IndeedSearch> connIndeedSearch() {
        RedisTemplate<String, IndeedSearch> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<String, Results> redisTemplate() {
        RedisTemplate<String, Results> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(redisConnectionFactory());
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }
/*

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisTemplate(), topic());
    }*/
}
