package com.example.util;

import cn.hutool.core.util.StrUtil;

public class StringUtil {
    
    /**数组拼接为字符串*/
    public static String join(Object[] array, String split) {
        return StrUtil.join(split, array);
    }

}
