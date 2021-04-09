package com.myweb.bookswap.service;

import com.myweb.bookswap.dao.BookRepository;
import com.myweb.bookswap.entity.Book;
import com.myweb.bookswap.entity.Genre;
import com.myweb.bookswap.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


//implement functional interface
@Service
public class BookServiceImpl implements BookInfoService {

    @Autowired
    BookRepository bookRepository;

    @Value("${pagebooksno}")
    int pagebooksno;


    public Page<Book> getAllBooks(int pageNo, String sortField, String sortDir, String keyword) {

        Sort sort=Sort.by(sortField);
        sort=sortDir.equals("asc")?sort.ascending():sort.descending();


        Pageable pageable= PageRequest.of(pageNo-1,pagebooksno,sort);

        if(keyword!=null)
        {
          return   bookRepository.find(keyword.toLowerCase(),pageable);
        }

        return bookRepository.findAll(pageable);

        

    }



	@Override
	public Book getBook(int id) {
		// TODO Auto-generated method stub
		return bookRepository.findById(id).get();
	}

	@Override
	public List<Book> getBookByName(String name) {
		return bookRepository.findByBookname(name);
	}

	@Override
	public Page<Book> getBooksOfOwner(User user) {
		
		Page<Book> books=getBooksOfOwner(user,1,"bookname", "asc");
		
		return books;
	}
	

	@Override
	public Page<Book> getBooksOfOwner(User user,int pageNo, String sortField, String sortDir ) {
		
		Sort sort=Sort.by(sortField);
		sort=sortDir.equals("asc")?sort.ascending():sort.ascending();
		
		Pageable pageable=PageRequest.of(pageNo-1, 5,sort);
		
		
		return bookRepository.findByBookowner(user,pageable);
		
		
	}

	


	@Override
	public Page<Book> getBooksOfAuthor(String authorname) {
		// TODO Auto-generated method stub
		Sort sort=Sort.by("bookname");
		//sort=sortDir.equals("asc")?sort.ascending():sort.ascending();
		
		Pageable pageable=PageRequest.of(0, pagebooksno,sort);
        Page<Book> books=bookRepository.findByBookauthor(authorname, pageable);
		
		return books;
	}



	@Override
	public Page<Book> getBooksByGenre(String genre) {
		// TODO Auto-generated method stub
		
		
		return getBooksByGenre(genre, 1, "bookname", "asc");
	}


	
	public Page<Book> getBooksByGenre(String genre,int pageNo, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=Sort.by("bookname");
		sort=sort.ascending();
		
		Pageable pageable=PageRequest.of(pageNo-1, pagebooksno,sort);
		
		
		Page<Book> books=bookRepository.findByBookgenres(genre, pageable);
		
		return books;
	}



	@Override
	public List<String> getAllAuthors() {
		
		return bookRepository.findAuthors();
	}



	@Override
	public Page<Book> getBooksByAuthor(String authorname) {
		// TODO Auto-generated method stub
		return getBooksByAuthor(authorname, 1, "bookname", "asc");
	}


	

	public Page<Book> getBooksByAuthor(String authorname,int pageNo, String sortField, String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=Sort.by("bookname");
		sort=sort.ascending();
		
		Pageable pageable=PageRequest.of(pageNo-1, pagebooksno,sort);
		
		
		Page<Book> books=bookRepository.findByBookauthor(authorname, pageable);
		
		return books;
	}
	
	



	
	

}
