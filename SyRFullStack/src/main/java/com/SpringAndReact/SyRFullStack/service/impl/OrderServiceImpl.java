package com.SpringAndReact.SyRFullStack.service.impl;

import com.SpringAndReact.SyRFullStack.dto.OrderDetailDto;
import com.SpringAndReact.SyRFullStack.dto.OrderDto;
import com.SpringAndReact.SyRFullStack.entity.Client;
import com.SpringAndReact.SyRFullStack.entity.Meal;
import com.SpringAndReact.SyRFullStack.entity.Order;
import com.SpringAndReact.SyRFullStack.entity.OrderDetail;
import com.SpringAndReact.SyRFullStack.exception.ResourceNotFoundException;
import com.SpringAndReact.SyRFullStack.repository.ClientRepository;
import com.SpringAndReact.SyRFullStack.repository.MealRepository;
import com.SpringAndReact.SyRFullStack.repository.OrderDetailRepository;
import com.SpringAndReact.SyRFullStack.repository.OrderRepository;
import com.SpringAndReact.SyRFullStack.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public OrderDto addOrder(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        Client client = clientRepository.findById(orderDto.getClientId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Client id not found: " + orderDto.getClientId())
                );
        order.setClient(client);
        order = orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order not found: "+orderId)
                );

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream()
                .map((order)-> modelMapper
                        .map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto UpdateOrder(Long orderId, OrderDto orderDto) {
        Order orderToUpdate = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order not found: "+orderId)
                );
        orderToUpdate.setOrderDateIn(orderDto.getOrderDateIn());
        orderToUpdate.setTotal_amount(orderDto.getTotal_amount());
        Client client = clientRepository.findById(orderDto.getClientId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("client not found: " +orderDto.getClientId())
                );
        orderToUpdate.setClient(client);
        orderToUpdate = orderRepository.save(orderToUpdate);
       return modelMapper.map(orderToUpdate, OrderDto.class);
    }

    @Override
    public OrderDto closeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order not found: "+orderId)
                );
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        BigDecimal totalAmount = orderDetails.stream()
                .map(detail -> detail.getMeal().getPrice().multiply(BigDecimal.valueOf(detail.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotal_amount(totalAmount);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order orderToDelete = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Order Not found: "+orderId)
                );
        orderRepository.delete(orderToDelete);
    }

}
