package com.getir.readingisgoodapp.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateBookStockRequest
{
    private int stock;
    private String id;

    public UpdateBookStockRequest(int stock, String id)
    {
        this.stock = stock;
        this.id = id;
    }
}
