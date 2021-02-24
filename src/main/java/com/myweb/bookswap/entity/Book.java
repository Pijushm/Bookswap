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
    String booknameinbangla;
    String bookauthor;
    @Column(nullable = false)
    String bookcondition;
    String bookdescription;
    int bookcatog;
    int booklang;
    int buy_avl;
    @Transient
    boolean avl_to_buy;

    int lend_avl;
    @Transient
    boolean avl_to_lend;

    int free;
    @Transient
    boolean freebook;

    int price;

    @ManyToOne
    User bookowner;

    @ManyToMany
    @JoinTable(name = "genre_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))/**
    The inverse side uses the mappedBy attribute to say: "I'm the inverse side. " +
            "Go see the users attribute in the target entity to see how this association is mapped."*/
    List<Genre> bookgenres;



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



    public String getBooknameinbangla() {
        return booknameinbangla;
    }

    public void setBooknameinbangla(String booknameinbangla) {
        this.booknameinbangla = booknameinbangla;
    }

    public int getBuy_avl() {
        return buy_avl;
    }

    public void setBuy_avl(int buy_avl) {
        this.buy_avl = buy_avl;
    }

    public int getLend_avl() {
        return lend_avl;
    }

    public void setLend_avl(int lend_avl) {
        this.lend_avl = lend_avl;
    }

    public int getFree() {

        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public boolean isAvl_to_buy() {


        return avl_to_buy;
    }

    public void setAvl_to_buy(boolean avl_to_buy) {

        if(avl_to_buy)
            this.buy_avl=1;
        else this.buy_avl=0;

        this.avl_to_buy = avl_to_buy;
    }

    public boolean isAvl_to_lend() {
        return avl_to_lend;
    }

    public void setAvl_to_lend(boolean avl_to_lend) {

        if(avl_to_lend)
            this.lend_avl=1;
        else this.lend_avl=0;
        this.avl_to_lend = avl_to_lend;
    }

    public boolean isFreebook() {

        return freebook;
    }

    public void setFreebook(boolean freebook) {

        if(freebook)
            this.free=1;
        else this.free=0;

        this.freebook = freebook;
    }

    public List<Genre> getBookgenres() {
        return bookgenres;
    }

    public void setBookgenres(List<Genre> bookgenres) {
        this.bookgenres = bookgenres;
    }


}
