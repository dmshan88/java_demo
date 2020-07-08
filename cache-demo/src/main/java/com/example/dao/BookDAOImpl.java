package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.pojo.Book;
import com.example.service.CacheService;

@Component
public class BookDAOImpl implements BookDAO {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheService cacheService;
    
    @Cacheable(cacheNames = "books", key = "#id")
    public Book getData(String id) {
        Book book = new Book();
        book.setId((int) System.currentTimeMillis());
        book.setName("aaa" + id);
        simulateSlowService();
        return book;
    }

    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Book getData1(String id) {
        if (id == null) {
            id = "aaa";
        }
        Book book =  cacheService.getValue("books", id, Book.class);
        if (book == null) {
          book = new Book();
          book.setId((int) System.currentTimeMillis());
          book.setName("bbb" + id);
          simulateSlowService();
          cacheService.setValue("books", id, book);
        }
        return book;
    }
    
    @Override
    public Book getData2(String id) {
        if (id == null) {
            id = "aaa";
        }
        Cache cache = cacheManager.getCache("books");
        System.out.println(cache);
        if (cache.get(id) != null) {
            System.out.println(cache.get(id));
            return cache.get(id, Book.class);
        } else {
            Book book = new Book();
            book.setId((int) System.currentTimeMillis());
            book.setName("bbb" + id);
            simulateSlowService();
            cache.put(id, book);
            return book;
        }
    }

}
