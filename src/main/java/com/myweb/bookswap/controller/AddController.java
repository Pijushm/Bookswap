package com.myweb.bookswap.controller;


import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.dao.GenreRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AddController {

        @Autowired
        GenreRepository genreRepository;

        @Autowired
        BookRepository bookRepository;

        @GetMapping("/add")
        public String showAddingPage(Model model)
        {
            List<Genre> genreList=genreRepository.findAll();

            List<String> languages= Arrays.asList(
                    "Bangla","English","Other"
            );

            model.addAttribute("genrelist",genreList);
            model.addAttribute("languages",languages);
            model.addAttribute("book",new Book());

            return "add";
        }


        @PostMapping("/addbook")
        public String addBook(@ModelAttribute Book book,Model model )
        {
            bookRepository.save(book);

            return "redirect:/browse";

        }


}
