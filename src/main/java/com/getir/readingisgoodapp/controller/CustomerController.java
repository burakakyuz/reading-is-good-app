package com.getir.readingisgoodapp.controller;

import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.service.impl.CustomerServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController
{
    private final CustomerServiceImpl customerService;
    public CustomerController(CustomerServiceImpl service){
        this.customerService = service;
    }
    @PostMapping(value = "/save" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result save(@RequestBody Customer request) throws Exception
    {
        return customerService.newCustomer(request);
    }

    @GetMapping(value = "/getOrderByCustomer" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getCustomerOrders(@RequestParam String id, @RequestParam int page, @RequestParam int size)throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        return customerService.getCustomerOrders(id,pageable);
    }
}
