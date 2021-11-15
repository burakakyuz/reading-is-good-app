package com.getir.readingisgoodapp.service;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.model.UpdateBookStockRequest;

import java.util.List;
import java.util.Optional;

public interface BookService
{
    public Result save(Book book) throws Exception;

    public Optional<Book> findById(String id);

    public List<Book> findAllBooks();

    public Result updateBookStock(UpdateBookStockRequest updateBookStockRequest) throws Exception;
}
