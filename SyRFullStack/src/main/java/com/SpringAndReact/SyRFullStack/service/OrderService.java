package com.SpringAndReact.SyRFullStack.service;

import com.SpringAndReact.SyRFullStack.dto.OrderDto;
import com.SpringAndReact.SyRFullStack.entity.Order;

import java.util.List;
import java.util.Set;

public interface OrderService {
    OrderDto addOrder(OrderDto orderDto);
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getAllOrders();
    OrderDto UpdateOrder(Long orderId, OrderDto orderDto);
    OrderDto closeOrder(Long orderId);
    void deleteOrder(Long orderId);

}
