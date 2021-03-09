package com.myweb.bookswap.dao;

import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Integer> {


//    @Query("select b from Book b where lower(b.bookname) like lower(concat('%', :keyword,'%')) or lower(b.bookauthor) "
//    		+ "like lower(concat('%', :keyword,'%'))")
	
	
	@Query ("select b from Book b where lower(b.bookname) like %:keyword% or lower(b.bookauthor) like %:keyword%")
    Page<Book> find(@Param("keyword") String keyword,Pageable pageable);//keyword case sensitivity handled before calling
    
   

    Page<Book> findByBookowner(User user,Pageable pageable);
    Page<Book> findByBookauthor(String authorname,Pageable pageable);//what if duplicate author name??


}
