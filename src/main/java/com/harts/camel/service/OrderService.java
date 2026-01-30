package com.harts.camel.service;

import com.harts.camel.dto.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void initDB(){
        orders.add(new Order(45,"book",50.00));
        orders.add(new Order(89,"shoes",999.99));
        orders.add(new Order(470,"pixel 8",72000.99));
    }

    public Order addOrder(Order order){
        orders.add(order);
        return order;
    }

    public List<Order> getOrders(){
        return orders;
    }
}
