package com.SpringAndReact.SyRFullStack.controller;

import com.SpringAndReact.SyRFullStack.dto.OrderDetailDto;
import com.SpringAndReact.SyRFullStack.entity.OrderDetail;
import com.SpringAndReact.SyRFullStack.service.OrderDetailService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin("*")
@RestController
@RequestMapping("/api/food-order-app/orderDetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping
    public ResponseEntity<OrderDetailDto> createOrderDetail(@RequestBody OrderDetailDto orderDetailDto){
        OrderDetailDto orderDetail = orderDetailService.createOrderDetail(orderDetailDto);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<OrderDetailDto> getOrderDetailById(@PathVariable("id") Long orderDetailId){
        OrderDetailDto orderDetailDto = orderDetailService.getOrderDetailByIde(orderDetailId);
        return new ResponseEntity<>(orderDetailDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getAllOrdersDetails(){
        List<OrderDetailDto> orderDetailDtoList = orderDetailService.getAllOrderDetails();
        return new ResponseEntity<>(orderDetailDtoList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDetailDto> updateOrderDetail(@PathVariable("id") Long orderDetailId, @RequestBody OrderDetailDto orderDetailDto){
        OrderDetailDto orderDetailToUpdate = orderDetailService.updateOrderDetail(orderDetailId, orderDetailDto);
        return  new ResponseEntity<>(orderDetailToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteOrderDetail(@PathVariable(name = "id") Long orderDetailId){
        orderDetailService.deleteOrderDetail(orderDetailId);
        return ResponseEntity.ok("OrderDetail deleted successfully");
    }


}
