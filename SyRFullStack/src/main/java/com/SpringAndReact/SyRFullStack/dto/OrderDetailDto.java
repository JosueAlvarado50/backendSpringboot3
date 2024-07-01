package com.SpringAndReact.SyRFullStack.dto;

import com.SpringAndReact.SyRFullStack.entity.Meal;
import com.SpringAndReact.SyRFullStack.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;
    private Long orderId;
    private Long mealId;
    private int amount;
}
