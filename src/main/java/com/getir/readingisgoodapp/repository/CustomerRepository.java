package com.getir.readingisgoodapp.repository;

import com.getir.readingisgoodapp.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String>
{
}
