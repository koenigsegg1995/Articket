package com.maddog.articket.config;

//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
	
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {
//        RedisStandaloneConfiguration redisStandaloneConfiguration =
//            new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
//
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(30);
//        poolConfig.setMaxIdle(10);
//        poolConfig.setMinIdle(5);
//
//        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
//            .usePooling()
//            .poolConfig(poolConfig)
//            .build();
//
//        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//        return template;
//    }
    
}
