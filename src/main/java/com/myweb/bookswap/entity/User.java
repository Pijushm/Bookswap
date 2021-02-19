package com.myweb.bookswap.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bswapuser")
public class User {

    @Id
     int user_id;
     String firsname;
     String lastname;
     String email;
     String district;
     int   Gender;
     String password;


     @OneToMany(mappedBy = "bookowner",cascade = CascadeType.ALL)
     List<Book> owned_books;


    public User() {
    }

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getOwned_books() {
        return owned_books;
    }

    public void setOwned_books(List<Book> owned_books) {
        this.owned_books = owned_books;
    }

    public String getfullName()
    {
        return this.firsname+" "+this.lastname;
    }


}
