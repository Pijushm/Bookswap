package com.myweb.bookswap.service;

import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import com.myweb.bookswap.entity.User;

import java.util.List;

import org.springframework.data.domain.Page;


public interface BookInfoService  {

	Book getBook(int id);
	Page<Book> getBooksOfOwner(User user);
	Page<Book> getBooksOfOwner(User user,int pageNo, String sortField, String sortDir);
	Page<Book> getBooksOfAuthor(String authorname);
    Page<Book> getAllBooks(int pageNo, String sortField, String sortDir, String keyword);
    Page<Book> getBooksByGenre(String genre);
    Page<Book> getBooksByAuthor(String authorname);
   
    List<String> getAllAuthors();
    
}
