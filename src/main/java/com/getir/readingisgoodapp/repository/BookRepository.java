package com.getir.readingisgoodapp.repository;

import com.getir.readingisgoodapp.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book,String>
{
}
