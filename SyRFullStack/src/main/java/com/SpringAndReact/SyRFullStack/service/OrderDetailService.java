package com.SpringAndReact.SyRFullStack.service;

import com.SpringAndReact.SyRFullStack.dto.OrderDetailDto;

import java.util.List;


public interface OrderDetailService {
    OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto);
    OrderDetailDto getOrderDetailByIde(Long orderDetailId);
    List<OrderDetailDto> getAllOrderDetails();
    OrderDetailDto updateOrderDetail(Long orderDetailId, OrderDetailDto orderDetailDto);
    void deleteOrderDetail(Long orderDetailId);
}
