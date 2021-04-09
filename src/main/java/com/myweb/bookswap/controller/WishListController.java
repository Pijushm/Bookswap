package com.myweb.bookswap.controller;

import com.myweb.bookswap.dao.WishListRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.User;
import com.myweb.bookswap.entity.WishList;
import com.myweb.bookswap.service.BookInfoService;
import com.myweb.bookswap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
public class WishListController {

    @Autowired
    UserService userService;


    @Autowired
    WishListRepository wishrepo;

    @Autowired
    BookInfoService bookInfoService;

    @GetMapping("/add/wish")
    public String showAddWishPage()
    {

        return "addwish";
    }




    @GetMapping("/wish/{bookid}")
    public String addWishList(@PathVariable("bookid") String bookid) {

        return "";
    }

    //add common keyword either search by name or id
    @PostMapping("searchforwish")
    public String WishedBookSearch(@RequestParam("wishbookname") String wishbookname, Model model)
    {

        List<Book> abooks=bookInfoService.getBookByName(wishbookname.trim());

        //calculate if any user have this book to give away currently . if not
        //show only book info without user and if book is available then notify the user
       // that book is already available

        if(abooks!=null&&!abooks.isEmpty())
        {
            model.addAttribute("books",abooks);
            model.addAttribute("bookindb",Boolean.TRUE);
        }

        

        return "addwish";
    }

    @PostMapping("addwishbook")
    public String addWishedBook(@RequestParam("bookid") String bookid)
    {

        Optional<User> user = userService.getCurrentUser();

        if (user.isPresent()) {
            User currentuser = user.get();
            WishList wishList = new WishList();

            wishList.setBuser(currentuser);
            wishList.setBookid(Integer.parseInt(bookid));

            wishrepo.save(wishList);



            return "addwish";
        }


        return "addwish";
    }
}
