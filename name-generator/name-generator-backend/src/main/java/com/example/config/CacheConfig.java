package com.example.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.utils.CacheUtil;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    CacheUtil cacheUtil(CacheManager cacheManager) {
        return new CacheUtil(cacheManager);
    }
    
    public interface Names {
        
        public static String NAME_DTO = "NameDTOMap";
        
    }
}
