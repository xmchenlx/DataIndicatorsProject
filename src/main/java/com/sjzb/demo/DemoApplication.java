package com.sjzb.demo;

import com.sjzb.demo.service.StatisticsEntity.GeneralRedisServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.io.IOException;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.sjzb.demo.SessionListener")
@EnableCaching
public class DemoApplication {

    private static GeneralRedisServiceImpl redisService = new GeneralRedisServiceImpl();
    public static void main(String[] args) throws IOException {

//        redisService.goRun(5);
        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        //加载redis缓存的默认配置
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();

        configuration = configuration.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return configuration;
    }

}
