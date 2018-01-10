package com.landerer.bookstore.repositories;

import com.landerer.bookstore.model.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
