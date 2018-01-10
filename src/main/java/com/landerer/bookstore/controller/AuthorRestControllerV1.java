package com.landerer.bookstore.controller;

import com.landerer.bookstore.model.Author;
import com.landerer.bookstore.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/v1/authors")
public class AuthorRestControllerV1 {

    @Autowired
    private AuthorRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<GetAuthorResponse> createAuthor(@RequestBody CreateAuthorRequest request) {
        final Author author = new Author();
        author.setFirstName(request.firstName);
        author.setLastName(request.lastName);

        this.repository.save(author);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    public static final class CreateAuthorRequest {
        public String firstName;
        public String lastName;
    }

    public static final class GetAuthorResponse {
        public Long id;
        public String firstName;
        public String lastName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GetAuthorResponse> getAuthors() {
        return StreamSupport.stream(this.repository.findAll().spliterator(), false)
                .map(this::getGetAuthorResponse)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable Long id) {
        final Author author = this.repository.findOne(id);
        if(author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(getGetAuthorResponse(author));
    }

    private GetAuthorResponse getGetAuthorResponse(Author author) {
        final GetAuthorResponse response = new GetAuthorResponse();
        response.id = author.getId();
        response.firstName = author.getFirstName();
        response.lastName = author.getLastName();
        return response;
    }
}
