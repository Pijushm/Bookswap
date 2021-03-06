package com.myweb.bookswap.service;

import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookInfoService {

    @Autowired
    BookRepository bookRepository;

    @Value("${pagebooksno}")
    int pagebooksno;


    public Page<Book> getAllBooks(int pageNo, String sortField, String sortDir, String keyword) {

        Sort sort=Sort.by(sortField);
        sort=sortDir.equals("asc")?sort.ascending():sort.descending();


        Pageable pageable= PageRequest.of(pageNo-1,pagebooksno,sort);

        if(keyword!=null)
        {
          return   bookRepository.find(keyword,pageable);
        }

        return bookRepository.findAll(pageable);


    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public void setBookGenres(List<Genre> genres) {

    }


}
