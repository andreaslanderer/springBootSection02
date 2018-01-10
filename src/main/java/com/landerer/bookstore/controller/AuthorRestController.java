package com.landerer.bookstore.controller;

import com.landerer.bookstore.model.Author;
import com.landerer.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController("/authors")
public class AuthorRestController {

    @Autowired
    private AuthorRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CreateAuthorResponse> createAuthor(@RequestBody CreateAuthorRequest request) {
        final Author author = new Author();
        author.setFirstName(request.firstName);
        author.setLastName(request.lastName);

        this.repository.save(author);

        final CreateAuthorResponse response = new CreateAuthorResponse();
        response.id = author.getId();
        response.firstName = author.getFirstName();
        response.lastName = author.getLastName();
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    public static final class CreateAuthorRequest {
        public String firstName;
        public String lastName;
    }

    public static final class CreateAuthorResponse {
        public Long id;
        public String firstName;
        public String lastName;
    }
}
