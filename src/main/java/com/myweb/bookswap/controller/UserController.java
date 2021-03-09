package com.myweb.bookswap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.dao.UserRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.service.BookInfoService;

@Controller
public class UserController {
	
	
	@Autowired
	BookInfoService booksinfo;
	
	@Autowired
	UserRepository userinfo;
	
	
	@GetMapping("/{userno}/books/{currentPageNo}")
	public String getUserBooks(@PathVariable("userno")int userno,@PathVariable("currentPageNo")int currentPageNo,Model model)
	{
		   
	        Page<Book> bookspage=booksinfo.getBooksOfOwner(userinfo.findByUserno(userno));
	     	List<Book> ownerbooks=bookspage.getContent();
		
		
		    int totalpage=bookspage.getTotalPages();
	        long no_of_books=bookspage.getTotalElements();
	        model.addAttribute("ownerbooks", ownerbooks);
	        model.addAttribute("total_books",no_of_books);
	        model.addAttribute("total_page",totalpage);
	        model.addAttribute("currentpage",currentPageNo);
		
		    return "demouserbooks";
	}

}
