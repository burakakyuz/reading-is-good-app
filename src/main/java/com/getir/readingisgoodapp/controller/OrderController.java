package com.getir.readingisgoodapp.controller;


import com.getir.readingisgoodapp.domain.Order;
import com.getir.readingisgoodapp.model.Result;
import com.getir.readingisgoodapp.service.impl.OrderServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController
{
    private final OrderServiceImpl orderService;
    public OrderController(OrderServiceImpl service){
        this.orderService = service;
    }
    @PostMapping(value = "/save" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result save(@RequestBody Order request) throws Exception
    {
        return orderService.save(request);
    }

    @GetMapping("/getOrder")
    public Optional<Order> findById(@RequestParam String orderId)
    {
        return orderService.findByOrderId(orderId);
    }


    @GetMapping(value = "/getOrdersByDates")
    public List<Order> getOrdersByDates(@RequestParam String startDate,@RequestParam String endDate) throws Exception
    {

        return orderService.getOrdersByDates(ZonedDateTime.parse(startDate),ZonedDateTime.parse(endDate));
    }

}
