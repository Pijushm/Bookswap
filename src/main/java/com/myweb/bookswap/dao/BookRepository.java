package com.myweb.bookswap.dao;

import com.myweb.bookswap.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Integer> {


    @Query("select b from Book b where b.bookname like %?1% or b.bookauthor like %?1%")
    Page<Book> find(String keyword,Pageable pageable);
}
