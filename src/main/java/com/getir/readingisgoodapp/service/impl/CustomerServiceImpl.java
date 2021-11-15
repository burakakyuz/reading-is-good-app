package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.repository.CustomerRepository;
import com.getir.readingisgoodapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private String MESSAGE = "SUCCESS";

    private CustomerRepository repository;
    private MongoTemplate mongoTemplate;
    @Autowired
    public CustomerServiceImpl(CustomerRepository repo,MongoTemplate template){
        this.mongoTemplate = template;
        this.repository=repo;
    }
    @Override
    public Result newCustomer(Customer customer)
    {
        Result result = new Result();

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(customer.getEmail()));
        long count = mongoTemplate.count(query, Customer.class);
        if (count>0)
        {
            result.setCode(-1);
            result.setMessage("The customer has already been created with this email");
            return result;
        }
        repository.save(customer);
        result.setCode(0);
        result.setMessage(MESSAGE);
        return result;
    }

    @Override
    public List<Order> getCustomerOrders(String customerId, Pageable pageable) throws Exception
    {
        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("customerId").is(customerId));
        List<Order> orders = (mongoTemplate.find(query, Order.class));
        long count = mongoTemplate.count(query, Order.class);
        if(orders.isEmpty())
        {
            throw new Exception("No order found");
        }
        Page<Order> page = new PageImpl<Order>(orders,pageable,count);
        return page.toList();
    }

    public Optional<Customer> findById(String id) {
        return repository.findById(id);
    }
}
