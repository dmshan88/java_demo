package com.example.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**缓存工具类（需要CacheConfig.java）*/
public class CacheUtil {

    private CacheManager cacheManager;

    public CacheUtil(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**保存缓存*/
    public void setValue(String name, Object key, Object value) {
        Cache cache = cacheManager.getCache(name);
        cache.put(key, value);
    }

    /**读取缓存*/
    public <T> T getValue(String name, Object key, Class<T> clazz) {
        Cache cache = cacheManager.getCache(name);
        if (cache != null && cache.get(key) != null) {
            return cache.get(key, clazz);
        }
        return null;
    }

    /**删除缓存*/
    public void delete(String name, Object key) {
        Cache cache = cacheManager.getCache(name);
        cache.evict(key);
    }
    
    /**批量删除缓存*/
    public void batchDelete(String name, Object[] keyList) {
        for (Object key : keyList) {
            Cache cache = cacheManager.getCache(name);
            cache.evict(key);
        }
    }

    /**删除所有缓存*/
    public void deleteAll(String name) {
        Cache cache = cacheManager.getCache(name);
        cache.invalidate();
    }
}
