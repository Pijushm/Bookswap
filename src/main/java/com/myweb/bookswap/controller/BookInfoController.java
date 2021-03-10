package com.myweb.bookswap.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.service.BookInfoService;

@Controller
public class BookInfoController {

	
	
	    @Autowired
	    BookInfoService bookinfo;
	
	    @GetMapping("book/{bookid}/{bookname}")
	    public String showinfoPage(@PathVariable("bookid") String bookid,@PathVariable("bookname") String bookname
	    		,Model model)
	    {
	    	Book book=bookinfo.getBook(Integer.parseInt(bookid));
	    	
	    	List<Book> ownerotherbooks= new ArrayList<Book>(bookinfo.getBooksOfOwner(book.getBookowner()).getContent());
	    			
	     //java 8 
	    	ownerotherbooks.remove(book);
	    	ownerotherbooks=ownerotherbooks.stream().limit(5).collect(Collectors.toList());
	    	
	    	List<Book> authorotherbooks=bookinfo.getBooksOfAuthor(book.getBookauthor())
	    			               .stream().limit(5).collect(Collectors.toList());//java 8 
	    	
	    	model.addAttribute("book",book);
	    	model.addAttribute("ownerbooks",ownerotherbooks);
	    	model.addAttribute("authorbooks",authorotherbooks);
	    	return "singlebook";
	    	
	    }
	    
	    
}
