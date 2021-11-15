package com.getir.readingisgoodapp.service.impl;

import com.getir.readingisgoodapp.domain.Book;
import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.repository.BookRepository;
import com.getir.readingisgoodapp.repository.OrderRepository;
import com.getir.readingisgoodapp.service.StatisticsService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StatisticServiceImpl implements StatisticsService
{
    private OrderRepository orderRepo;
    private BookRepository bookRepo;
    private MongoTemplate mongoTemplate;

    public StatisticServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Long getTotalAmountOrder() {
        return mongoTemplate.count(null, Order.class);
    }

    @Override
    public Long getTotalAmountOfBooks() {
        return mongoTemplate.count(null, Book.class);
    }
}
