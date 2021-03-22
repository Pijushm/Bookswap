package com.myweb.bookswap.controller;

import com.myweb.bookswap.dao.GenreRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class DiscoverController {

    @Autowired
    BookInfoService bookService;
    
    @Autowired
    GenreRepository genreRepository;

    @GetMapping("/browse")
    public String browse(Model model, @Param("keyword") String keyword) {


        return goToPage(1, keyword, "bookname", "asc", model);
    }

    
    @GetMapping("/browse/genre/{genrename}")
    public String browseByGenre(Model model, @PathVariable("genrename") String genrename) {

    	Page<Book> pageinfo = bookService.getBooksByGenre(genrename);
    	return showPageContent(pageinfo, model,1);
    	
       
    }
    
    
    @GetMapping("/browse/author/{authorname}")
    public String browseByAuthor(Model model, @PathVariable("authorname") String authorname) {

    	Page<Book> pageinfo = bookService.getBooksByAuthor(authorname);
    	return showPageContent(pageinfo, model,1);
    	
       
    }
    

    @GetMapping("/browse/{currentPageNo}")
    public String goToPage(@PathVariable("currentPageNo") int currentPageNo, @Param("keyword") String keyword, @Param("sortField")
            String sortField, @Param("sortDir") String sortDir, Model model
    ) {

    	//you can try to use functional interface later to send a method to get whether to call 
    	//getAllbooks or getBygenre etc.
        Page<Book> pageinfo = bookService.getAllBooks(currentPageNo, "bookname", "asc", keyword);
        
        return showPageContent(pageinfo, model,currentPageNo);
    }
    
    
    public String showPageContent(Page<Book> pageinfo,Model model,int currentPageNo)
    {
    	   List<Book> books = pageinfo.getContent();
    	
    	  int totalpage=pageinfo.getTotalPages();
          long no_of_books=pageinfo.getTotalElements();
          model.addAttribute("books", books);
          model.addAttribute("total_books",no_of_books);
          model.addAttribute("total_page",totalpage);
          model.addAttribute("currentpage",currentPageNo);
          model.addAttribute("genres", genreRepository.findAll());
          model.addAttribute("authors", bookService.getAllAuthors());
    	  return "discover";
    }


}
