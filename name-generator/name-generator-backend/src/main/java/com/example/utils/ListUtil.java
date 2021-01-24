package com.example.utils;

import java.util.List;

import org.apache.commons.collections4.ListUtils;

public class ListUtil {

    /**分割*/
    public static <T> List<List<T>> partition(List<T> list, int size) {
        return ListUtils.partition(list, size);
    }
}
