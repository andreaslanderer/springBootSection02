package com.landerer.bookstore.bootstrap;

import com.landerer.bookstore.model.Author;
import com.landerer.bookstore.model.Book;
import com.landerer.bookstore.repositories.AuthorRepository;
import com.landerer.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.initData();
    }

    private void initData() {
        final Author eric = new Author("Eric", "Evans");
        final Book ddd = new Book("Domain Driven Design", "1-23456789-0", "Harper Collins");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        this.authorRepository.save(eric);
        this.bookRepository.save(ddd);
    }
}
