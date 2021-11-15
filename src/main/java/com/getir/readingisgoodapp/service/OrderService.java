package com.getir.readingisgoodapp.service;


import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.model.Result;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService
{
    public Result save(Order order) throws Exception;
    public Optional<Order> findByOrderId(String orderId);
    public List<Order> getOrdersByDates(ZonedDateTime startDate, ZonedDateTime endDate);


}
