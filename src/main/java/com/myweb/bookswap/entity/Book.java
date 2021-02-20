package com.myweb.bookswap.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false)
    int bookid;

    @Column(nullable = false)
    String bookname;
    String bookauthor;
    @Column(nullable = false)
    String bookcondition;
    String bookdescription;
    int bookcatog;
    int booklang;
    int buy_avl;
    int lend_avl;
    int free;
    int price;

    @ManyToOne
    User bookowner;

    @ManyToMany(mappedBy = "books")
    List<Genre> genres;


    public Book() {
    }

    public int getBookid() {
        return bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public String getBookcondition() {
        return bookcondition;
    }

    public void setBookcondition(String bookcondition) {
        this.bookcondition = bookcondition;
    }

    public String getBookdescription() {
        return bookdescription;
    }

    public void setBookdescription(String bookdescription) {
        this.bookdescription = bookdescription;
    }

    public int getBookcatog() {
        return bookcatog;
    }

    public void setBookcatog(int bookcatog) {
        this.bookcatog = bookcatog;
    }

    public int getBooklang() {
        return booklang;
    }

    public void setBooklang(int booklang) {
        this.booklang = booklang;
    }

    public int isBuy_avl() {
        return buy_avl;
    }

    public void setBuy_avl(int buy_avl) {
        this.buy_avl = buy_avl;
    }

    public int isLend_avl() {
        return lend_avl;
    }

    public void setLend_avl(int lend_avl) {
        this.lend_avl = lend_avl;
    }

    public int isFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getBookowner() {
        return bookowner;
    }

    public void setBookowner(User bookowner) {
        this.bookowner = bookowner;
    }
}
