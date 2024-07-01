package com.SpringAndReact.SyRFullStack.controller;

import com.SpringAndReact.SyRFullStack.dto.MealDto;
import com.SpringAndReact.SyRFullStack.entity.Meal;
import com.SpringAndReact.SyRFullStack.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/food-order-app/meal")
@CrossOrigin("*")
@RestController
public class MealController {
    @Autowired
    private MealService mealService;

    @PostMapping
    public ResponseEntity<MealDto> addMeal(@RequestBody MealDto mealDto){
        MealDto mealToCreate = mealService.addMeal(mealDto);
        return new ResponseEntity<>(mealToCreate, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MealDto>> getAllMeals(){
        List<MealDto> mealDtoList = mealService.getAllMeals();
        return new ResponseEntity<>(mealDtoList, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<MealDto> getMealById(@PathVariable(name = "id") Long mealId){
        MealDto mealDto = mealService.getMealById(mealId);
        return new ResponseEntity<>(mealDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<MealDto> updateMeal(@PathVariable(name = "id") Long mealId, @RequestBody MealDto mealDto){
        MealDto updatedMeal = mealService.updateMeal(mealId, mealDto);
        return new ResponseEntity<>(updatedMeal, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMeal(@PathVariable("id") Long mealId){
        mealService.deleteMeal(mealId);
        return ResponseEntity.ok("Meal was deleted successfully");
    }

}
