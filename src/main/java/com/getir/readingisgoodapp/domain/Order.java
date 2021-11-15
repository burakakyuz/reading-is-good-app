package com.getir.readingisgoodapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = "order")
@Data
@EqualsAndHashCode(of = "id")
public class Order
{
    @Id
    private String id;
    private String customerId;
    private List<String> books;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private String orderStatus;
    private int amount;

}
