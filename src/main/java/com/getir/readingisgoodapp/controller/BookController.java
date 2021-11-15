package com.getir.readingisgoodapp.controller;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.model.UpdateBookStockRequest;
import com.getir.readingisgoodapp.service.impl.BookServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController
{

    private final BookServiceImpl bookService;
    public BookController(BookServiceImpl service)
    {
        this.bookService = service;
    }

    @PostMapping(value = "/save" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result save(@RequestBody Book bookRequest) throws Exception {

        return bookService.save(bookRequest);
    }

    @GetMapping("/{id}")
    public Optional<Book> findById(@PathVariable("id") String id)
    {
        return bookService.findById(id);
    }

    @PutMapping(value="/updateBookStock", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result updateBookStock(@RequestBody UpdateBookStockRequest updateBookStockRequest) throws Exception {
     return bookService.updateBookStock(updateBookStockRequest);
    }

}
