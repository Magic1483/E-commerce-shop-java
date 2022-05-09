package com.h2.db.service;

import com.h2.db.model.OrderEntity;
import com.h2.db.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    public OrderEntity createOrder(OrderEntity entity)
    {
        entity=repository.save(entity);
        return entity;
    }
}
