package com.getir.readingisgoodapp.controller;


import com.getir.readingisgoodapp.service.impl.StatisticServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController
{

    private final StatisticServiceImpl statisticsService;

    public StatisticsController(StatisticServiceImpl statisticsService) {
        this.statisticsService = statisticsService;
    }


    @GetMapping(value = "/getTotalAmountOfOrder")
    public Long getTotalAmountOfOrder() {
        return statisticsService.getTotalAmountOrder();
    }

    @GetMapping(value = "/getTotalAmountOfBooks")
    public Long getTotalAmountOfBook() {
        return statisticsService.getTotalAmountOfBooks();
    }
}
