package com.myweb.bookswap.service;

import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;

import java.util.List;

public interface BookInfoService  {

    List<Book> getAllBooks();
    void setBookGenres(List<Genre> genres);
}
