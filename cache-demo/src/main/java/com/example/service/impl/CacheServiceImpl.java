package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.example.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {
    
    @Autowired
    private CacheManager cacheManager;

    @Override
    public void setValue(String name, String key, Object value) {
        Cache cache = cacheManager.getCache(name);
        cache.put(key, value);
    }

    @Override
    public <T> T getValue(String name, String key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(name);
        if (cache != null && cache.get(key) != null) {
            return cache.get(key, clazz);
        }
        return null;
    }

    @Override
    public void delete(String name, String key) {
        Cache cache = cacheManager.getCache(name);
        cache.evict(key);
    }

    @Override
    public void deleteAll(String name) {
        Cache cache = cacheManager.getCache(name);
        cache.invalidate();
    }
    
}
