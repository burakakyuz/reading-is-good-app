package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.model.UpdateBookStockRequest;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private String MESSAGE = "SUCCESS";

    private BookRepository repository;
    private MongoTemplate mongoTemplate;

    @Autowired
    public BookServiceImpl(BookRepository repository, MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
        this.repository = repository;
    }

    @Override
    public Result save(Book book) throws Exception
    {
        Result result = new Result();
        if (book.getId() != null)
        {
            Optional<Book> oldBook = findById(book.getId());
            if (oldBook.isPresent())
            {
                result.setCode(-1);
                result.setMessage("The book has already been created with this id: " +book.getId());
            }
        }
        repository.save(book);
        result.setCode(0);
        result.setMessage(MESSAGE);
        return result;
    }

    @Override
    public Optional<Book> findById(String id) {
        return repository.findById(id);
    }


    @Override
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Override
    public Result updateBookStock(UpdateBookStockRequest updateBookStockRequest) throws Exception
    {
        Result result = new Result();
        if (updateBookStockRequest.getStock() < 0)
        {
            result.setCode(-1);
            result.setMessage("book stock should be greater than zero");
        }
        Optional<Book> book = findById(updateBookStockRequest.getId());
        if (book.isEmpty())
        {
            result.setCode(-1);
            result.setMessage("no book found with this id: " +updateBookStockRequest.getId());
        }
        book.ifPresent(value -> value.setStock(updateBookStockRequest.getStock()));
        repository.save(book.get());
        result.setCode(0);
        result.setMessage(MESSAGE);
        return result;
    }
}
