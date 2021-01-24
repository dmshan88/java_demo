package com.example.utils;

public class BeanUtil {
    
    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

}
