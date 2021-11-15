package com.getir.readingisgoodapp.repository;

import com.getir.readingisgoodapp.domain.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order,String>
{
}
