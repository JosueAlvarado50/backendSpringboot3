package com.SpringAndReact.SyRFullStack.controller;

import com.SpringAndReact.SyRFullStack.dto.OrderDetailDto;
import com.SpringAndReact.SyRFullStack.dto.OrderDto;
import com.SpringAndReact.SyRFullStack.entity.Order;
import com.SpringAndReact.SyRFullStack.service.OrderDetailService;
import com.SpringAndReact.SyRFullStack.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/food-order-app/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto){
        OrderDto orderToCreate = orderService.addOrder(orderDto);
        return new ResponseEntity<>(orderToCreate, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") Long orderId){
        OrderDto orderDto = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orderDtoList = orderService.getAllOrders();
        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable(name = "id") Long orderId, @RequestBody OrderDto orderDto){
        OrderDto orderToUpdate = orderService.UpdateOrder(orderId, orderDto);
        return new ResponseEntity<>(orderToUpdate, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order was deleted successfully");
    }

    @PostMapping("/{orderId}/order-details")
    public ResponseEntity<OrderDto> addOrderDetailToOrder(@PathVariable Long orderId, @RequestBody OrderDetailDto orderDetailDto){
    orderDetailDto.setOrderId(orderId);
    orderDetailService.createOrderDetail(orderDetailDto);//crear el detalle de orden
        //obtener la orden actualizada despues de agregar el detalle
        OrderDto updatedOrderDto = orderService.getOrderById(orderId);

        return new ResponseEntity<>(updatedOrderDto, HttpStatus.OK);
    }
    @PutMapping("/close/{id}")
    public ResponseEntity<OrderDto> closeOrder(@PathVariable Long orderId){
        OrderDto orderDto = orderService.closeOrder(orderId);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);

    }


}
