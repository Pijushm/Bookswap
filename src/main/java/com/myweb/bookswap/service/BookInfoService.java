package com.myweb.bookswap.service;

import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookInfoService  {

    Page<Book> getAllBooks(int pageNo, String sortField, String sortDir, String keyword);
    List<Book> getAllBooks();
    void setBookGenres(List<Genre> genres);
}
