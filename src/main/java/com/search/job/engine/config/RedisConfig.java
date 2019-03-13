package com.search.job.engine.config;

import com.search.job.engine.service.MessagePublisher;
import com.search.job.engine.service.MessagePublisherImpl;
import com.search.job.engine.service.MessageSubscriber;
import com.search.job.indeed.models.response.IndeedSearch;
import com.search.job.indeed.models.response.Results;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
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
    public RedisTemplate<String, Object> redisGenericTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
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

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("messageQueue");
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new MessagePublisherImpl(redisGenericTemplate(), topic());
    }


    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new MessageSubscriber());
    }
}
