package com.myweb.bookswap.controller;

import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DiscoverController {

      @Autowired
      BookInfoService bookService;

      @GetMapping("/browse")
      public String browse(Model model)
      {
          List<Book> books=bookService.getAllBooks();
          System.out.println(books);
          for(Book book:books)
          {
              System.out.println(book.getBookname());
          }

          model.addAttribute("books",books);



          return  "discover";
      }

}
