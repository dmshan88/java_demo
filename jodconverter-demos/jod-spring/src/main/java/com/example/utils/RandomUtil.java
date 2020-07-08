package com.example.utils;

public class RandomUtil {

    /** 生成随机n位数字 */
    public static String createRandomNum(int num) {
        String randomNumStr = "";
        for (int i = 0; i < num; i++) {
            int randomNum = (int) (Math.random() * 10);
            randomNumStr += randomNum;
        }
        return randomNumStr;
    }

    /**生成随机文件名*/
    public static String randomFileName() {
        int random = (int) (1000 * Math.random());
        String fileName = System.currentTimeMillis() + random + "";
        return fileName;
    }
}
