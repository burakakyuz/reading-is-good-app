package com.getir.readingisgoodapp.service;

import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.model.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService
{
    public Result newCustomer(Customer customer) throws Exception;
    public List<Order> getCustomerOrders(String customerId, Pageable pageable) throws Exception;

}
