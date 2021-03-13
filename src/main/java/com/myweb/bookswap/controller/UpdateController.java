package com.myweb.bookswap.controller;

import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.dao.GenreRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class UpdateController {

    @Value("${upload.path}")
    String bookpath;


    @Autowired
    GenreRepository genreRepository;

    @Autowired
    BookRepository bookRepository;



    @PostMapping("/editbook")
    public String showEdit(@RequestParam("bookid") String bookid, Model model)
    {

        Book book=bookRepository.findById(Integer.parseInt(bookid)).get();

        List<Genre> genreList=genreRepository.findAll();

        List<String> languages= Arrays.asList(
                "Bangla","English","Other"
        );
        model.addAttribute("genrelist",genreList);
        model.addAttribute("languages",languages);
        model.addAttribute("book",book);
        model.addAttribute("bookid",bookid);
        return "add";

    }


    @PostMapping("/updatebook")
    public String UpdateBookInfo(@RequestParam("bookid") String bookid, Model model)
    {

        Book book=bookRepository.findById(Integer.parseInt(bookid)).get();

        List<Genre> genreList=genreRepository.findAll();

        List<String> languages= Arrays.asList(
                "Bangla","English","Other"
        );
        model.addAttribute("genrelist",genreList);
        model.addAttribute("languages",languages);
        model.addAttribute("book",book);
        model.addAttribute("bookid",bookid);

        return "add";

    }




}
