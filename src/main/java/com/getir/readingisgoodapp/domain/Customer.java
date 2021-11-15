package com.getir.readingisgoodapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
@Data
@EqualsAndHashCode(of = "id")
public class Customer
{
    private String id;
    private String name;
    private String surname;
    private String email;

    public Customer(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
