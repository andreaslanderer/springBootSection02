package com.landerer.bookstore.bootstrap;

import com.landerer.bookstore.model.Author;
import com.landerer.bookstore.model.Book;
import com.landerer.bookstore.model.Publisher;
import com.landerer.bookstore.repositories.AuthorRepository;
import com.landerer.bookstore.repositories.BookRepository;
import com.landerer.bookstore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    @Autowired
    public DevBootstrap(AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.initData();
    }

    private void initData() {
        final Author eric = new Author("Eric", "Evans");
        final Book ddd = new Book("Domain Driven Design", "1-23456789-0");
        final Publisher addisonWesley = new Publisher("Addison Wesley", "Boston", "USA");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(addisonWesley);
        this.authorRepository.save(eric);
        this.publisherRepository.save(addisonWesley);
        this.bookRepository.save(ddd);
    }
}
