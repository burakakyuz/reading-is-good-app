package com.getir.readingisgoodapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
@Data
@EqualsAndHashCode(of = "id")
public class Book
{
    @Id
    private String id;
    private String name;
    private String code;
    private String author;
    private int stock;
    private String categoryId;

    public Book(String name, String code, String author, int stock, String categoryId) {
        this.name = name;
        this.code = code;
        this.author = author;
        this.stock = stock;
        this.categoryId = categoryId;
    }
}
