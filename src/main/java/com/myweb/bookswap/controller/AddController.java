package com.myweb.bookswap.controller;


import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.dao.GenreRepository;
import com.myweb.bookswap.dao.UserRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import com.myweb.bookswap.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class AddController {


       @Value("${upload.path}")
       String imagedir;
       
       @Value("#{'${bookconditions}'.split(',')}")
       List<String> bookconditions;
       

        @Autowired
        GenreRepository genreRepository;

        @Autowired
        BookRepository bookRepository;

        @Autowired
        UserRepository userRepository;

        @GetMapping("/add")
        public String showAddingPage(Model model)
        {
            List<Genre> genreList=genreRepository.findAll();


            List<String> languages= Arrays.asList(
                    "Bangla","English","Other"
            );

            model.addAttribute("genrelist",genreList);
            model.addAttribute("languages",languages);
            model.addAttribute("bookconditions",bookconditions);
            model.addAttribute("book",new Book());

            return "add";
        }


        @PostMapping("/addbook")
        public String addBook(@RequestParam("frontimage") MultipartFile imagefile, @ModelAttribute Book book, Model model ) throws IOException {
            if(imagefile==null)
            {
                System.out.println("Please Choose Front Cover");

            }
            else {

                long total_books=bookRepository.count();



                book.setBookowner(userRepository.getOne("Pijushm"));

               book= bookRepository.save(book);


                File folder=new File(imagedir+"/"+book.getBookid());
                if(!folder.exists())
                {
                    folder.mkdirs();

                }

                System.out.println(imagedir);
                System.out.println("folder "+folder.getName());

                String imagefilename=imagefile.getOriginalFilename();

                byte bytes[]=imagefile.getBytes();
                Path path= Paths.get(folder.toPath()+"/"+"frontcover"+imagefilename.substring(imagefilename.lastIndexOf(".")));

                //if database connection is lost files wont be write
                Files.write(path,bytes);
            }



           // return "redirect:/browse";

            model.addAttribute("bookid",book.getBookid());
            return "add2";
        }



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
            model.addAttribute("bookconditions",bookconditions);
            model.addAttribute("bookid",bookid);

            return "add";
            
        }


}
