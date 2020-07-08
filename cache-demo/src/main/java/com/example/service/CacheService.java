package com.example.service;

public interface CacheService {
    
    void setValue(String name, String key, Object value);
    
    <T> T getValue(String name, String key, Class<T> clazz);
    
    void delete(String name, String key);
    
    void deleteAll(String name);

}
