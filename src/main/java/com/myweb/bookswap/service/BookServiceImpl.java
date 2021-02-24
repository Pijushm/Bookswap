package com.myweb.bookswap.service;

import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookInfoService {

    @Autowired
    BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void setBookGenres(List<Genre> genres) {

    }


}
