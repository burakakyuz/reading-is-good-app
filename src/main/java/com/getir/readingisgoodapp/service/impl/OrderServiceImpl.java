package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.domain.Customer;
import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService
{
    private String MESSAGE = "SUCCESS";

    private MongoTemplate mongoTemplate;
    private OrderRepository orderRepository;
    private BookRepository bookRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MongoTemplate mongoTemplate)
    {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Result save(Order order) throws Exception
    {
        Result result = new Result();
        if ((order.getBooks().isEmpty() || order.getCustomerId()== null))
        {
            result.setCode(-1);
            result.setMessage("Can't create order without Book or Customer");
        }
        for (String bookId: order.getBooks())
        {
            Book bk = mongoTemplate.findById(bookId, Book.class);
            if (bk == null)
            {
                result.setCode(-1);
                result.setMessage("No book with such a id:"+ bookId);
            }
            if (bk.getStock() <= 0)
            {
                result.setCode(-1);
                result.setMessage("No stock for book");
            }
        }
        Customer customer = mongoTemplate.findById(order.getCustomerId(), Customer.class);
        if (customer == null)
        {
            result.setCode(-1);
            result.setMessage("Couldn't find customer with id:" + order.getCustomerId());
        }
        updateStockForBooks(order.getBooks());
        orderRepository.save(order);
        result.setCode(0);
        result.setMessage("Success");
        return result;
    }

    @Override
    public Optional<Order> findByOrderId(String orderId)
    {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getOrdersByDates(ZonedDateTime startDate, ZonedDateTime endDate)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("startDate").gte(startDate).lt(endDate));
        return mongoTemplate.find(query,Order.class);

    }

    private void updateStockForBooks(List<String> books)
    {
        for (String bookId : books)
        {
            Book book = mongoTemplate.findById(bookId, Book.class);
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(bookId));
            Update update = new Update();
            update.set("stock", book.getStock()-1);
            mongoTemplate.findAndModify(query, update,
                    FindAndModifyOptions.options().returnNew(true), Book.class);
            bookRepository.save(book);
        }
    }
}
