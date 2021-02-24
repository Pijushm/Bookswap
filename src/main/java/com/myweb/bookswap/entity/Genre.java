package com.myweb.bookswap.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Genre {

      @Id
      private int genre_id;
      private String name;

      @ManyToMany(mappedBy = "bookgenres")
      private List<Book> books;


    public int getGenre_id() {
        return genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
