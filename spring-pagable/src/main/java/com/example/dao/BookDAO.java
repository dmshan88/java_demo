package com.example.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pojo.Book;

public interface BookDAO extends JpaRepository<Book, Integer> {
    Page<Book> findByNameNotNull(Pageable pageable);

}
