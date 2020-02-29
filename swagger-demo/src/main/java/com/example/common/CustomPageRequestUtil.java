package com.example.common;

public class CustomPageRequestUtil {

    static final public String REQUEST_PARAM_PAGE = "page";
    static final public String REQUEST_PARAM_SIZE = "limit";
    static final private int defaultPage = 1;
    static final private int defaultSize = 10;
    static final private int maxSize = 1000;
    
    private final int page;

    private final int size;

    public static CustomPageRequestUtil build(Integer page, Integer size) {
        return new CustomPageRequestUtil(page, size);
    }

    private CustomPageRequestUtil(Integer page, Integer size) {
        this.page = (page == null || page <= 0) ? defaultPage : page;
        this.size = (size == null || size <= 0) ? defaultSize : (size > maxSize) ? maxSize : size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

}
