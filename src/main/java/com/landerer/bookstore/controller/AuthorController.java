package com.landerer.bookstore.controller;

import com.landerer.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ui/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String getBooks(Model model) {
        model.addAttribute("authors", this.repository.findAll());
        return "authors";
    }
}
