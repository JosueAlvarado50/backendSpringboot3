package com.SpringAndReact.SyRFullStack.service.impl;

import com.SpringAndReact.SyRFullStack.dto.OrderDetailDto;
import com.SpringAndReact.SyRFullStack.entity.Meal;
import com.SpringAndReact.SyRFullStack.entity.Order;
import com.SpringAndReact.SyRFullStack.entity.OrderDetail;
import com.SpringAndReact.SyRFullStack.exception.ResourceNotFoundException;
import com.SpringAndReact.SyRFullStack.repository.MealRepository;
import com.SpringAndReact.SyRFullStack.repository.OrderDetailRepository;
import com.SpringAndReact.SyRFullStack.repository.OrderRepository;
import com.SpringAndReact.SyRFullStack.service.OrderDetailService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public OrderDetailDto createOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = modelMapper.map(orderDetailDto, OrderDetail.class);
        Order order = orderRepository.findById(orderDetailDto.getOrderId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("order Not found: "+ orderDetailDto.getOrderId())
                );
        Meal meal = mealRepository.findById(orderDetailDto.getMealId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("meal not found: "+ orderDetailDto.getMealId())
                );
        orderDetail.setOrder(order);
        orderDetail.setMeal(meal);
        orderDetail = orderDetailRepository.save(orderDetail);

        return modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public OrderDetailDto getOrderDetailByIde(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("orderDetail not found: "+ orderDetailId)
                );

        return modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public List<OrderDetailDto> getAllOrderDetails() {
        List<OrderDetail> orderDetailDtoList = orderDetailRepository.findAll();
        return orderDetailDtoList.stream()
                .map((orderDetail) -> modelMapper.map(orderDetail, OrderDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDto updateOrderDetail(Long orderDetailId, OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("orderDetail not found: "+ orderDetailId)
                );
        Order order = orderRepository.findById(orderDetailDto.getOrderId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Order not found: "+ orderDetailDto.getOrderId())
                        );
        Meal meal = mealRepository.findById(orderDetailDto.getMealId())
                        .orElseThrow(
                                () -> new ResourceNotFoundException("meal not found: " + orderDetailDto.getMealId())
                        );
        orderDetail.setAmount(orderDetailDto.getAmount());
        orderDetail.setOrder(order);
        orderDetail.setMeal(meal);
        orderDetail = orderDetailRepository.save(orderDetail);

        return modelMapper.map(orderDetail, OrderDetailDto.class);
    }

    @Override
    public void deleteOrderDetail(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order detail not found: "+ orderDetailId)
                );
        orderDetailRepository.delete(orderDetail);
    }
}
