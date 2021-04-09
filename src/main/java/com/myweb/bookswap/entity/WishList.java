package com.myweb.bookswap.entity;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int wish_id;



    @ManyToOne
    @JoinColumn(name = "username")
    User buser;

    int bookid;

    Date creation_date;


    public WishList()
    {
        this.creation_date= Calendar.getInstance().getTime();
    }


    public int getWish_id() {
        return wish_id;
    }

    public void setWish_id(int wish_id) {
        this.wish_id = wish_id;
    }

    public User getBuser() {
        return buser;
    }

    public void setBuser(User buser) {
        this.buser = buser;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
