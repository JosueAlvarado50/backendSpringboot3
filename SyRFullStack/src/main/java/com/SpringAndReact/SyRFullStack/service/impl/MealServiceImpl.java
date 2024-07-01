package com.SpringAndReact.SyRFullStack.service.impl;

import com.SpringAndReact.SyRFullStack.dto.MealDto;
import com.SpringAndReact.SyRFullStack.entity.Meal;
import com.SpringAndReact.SyRFullStack.exception.ResourceNotFoundException;
import com.SpringAndReact.SyRFullStack.repository.MealRepository;
import com.SpringAndReact.SyRFullStack.service.MealService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MealServiceImpl implements MealService {
    private MealRepository mealRepository;
    private ModelMapper modelMapper;

    @Override
    public MealDto addMeal(MealDto mealDto) {
        Meal meal = modelMapper.map(mealDto, Meal.class);
        Meal savedMeal = mealRepository.save(meal);
        return modelMapper.map(savedMeal, MealDto.class);
    }

    @Override
    public MealDto getMealById(Long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Meal Not found: "+ mealId)
                );
        return modelMapper.map(meal, MealDto.class);
    }

    @Override
    public List<MealDto> getAllMeals() {
        List<Meal> mealList = mealRepository.findAll();
        return mealList.stream()
                .map((meal) -> modelMapper
                        .map(meal, MealDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MealDto updateMeal(Long mealId, MealDto mealDto) {
        Meal mealToUpdate = mealRepository.findById(mealId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Meal not found: "+mealId)
                );
        mealToUpdate.setName(mealDto.getName());
        mealToUpdate.setDescription(mealDto.getDescription());
        mealToUpdate.setPrice(mealDto.getPrice());
        Meal savedMeal = mealRepository.save(mealToUpdate);
        return modelMapper.map(savedMeal, MealDto.class);
    }

    @Override
    public void deleteMeal(Long mealId) {
        Meal mealToDelete = mealRepository.findById(mealId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Meal not found")
                );
        mealRepository.deleteById(mealId);
    }
}
