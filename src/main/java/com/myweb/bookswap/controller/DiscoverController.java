package com.myweb.bookswap.controller;

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

import java.util.List;

@Controller
public class DiscoverController {

    @Autowired
    BookInfoService bookService;

    @GetMapping("/browse")
    public String browse(Model model, @Param("keyword") String keyword) {


        return goToPage(1, keyword, "bookname", "asc", model);
    }


    @GetMapping("/browse/{currentPageNo}")
    public String goToPage(@PathVariable("currentPageNo") int currentPageNo, @Param("keyword") String keyword, @Param("sortField")
            String sortField, @Param("sortDir") String sortDir, Model model
    ) {

        Page<Book> pageinfo = bookService.getAllBooks(currentPageNo, "bookname", "asc", keyword);

        List<Book> books = pageinfo.getContent();
        System.out.println(books);
        for (Book book : books) {
            System.out.println(book.getBookname());
        }

        int totalpage=pageinfo.getTotalPages();
        long no_of_books=pageinfo.getTotalElements();
        model.addAttribute("books", books);
        model.addAttribute("total_books",no_of_books);
        model.addAttribute("total_page",totalpage);
        model.addAttribute("currentpage",currentPageNo);


        return "discover";
    }


}
