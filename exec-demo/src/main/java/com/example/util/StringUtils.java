package com.example.util;

import java.util.Collection;

public class StringUtils {
    
    public static boolean isNullOrEmpty(String str) {
        return org.apache.commons.lang.StringUtils.isEmpty(str);
    }
    
    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equals(str1, str2);
    }
    
    public static String mid(String str, int pos, int len) {
        return org.apache.commons.lang.StringUtils.mid(str, pos, len);
    }
    
    public static String join(Collection collection, String separator) {
        return org.apache.commons.lang.StringUtils.join(collection, separator);
    }
    
    public static String join(Object[] array, String separator) {
        return org.apache.commons.lang.StringUtils.join(array, separator);
    }

    public static String[] split(String str, String separatorChars) {
        return org.apache.commons.lang.StringUtils.split(str, separatorChars);
    }
}
