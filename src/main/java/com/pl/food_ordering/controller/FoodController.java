package com.pl.food_ordering.controller;

import com.pl.food_ordering.entity.Food;
import com.pl.food_ordering.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/foods")
    public Food createFood(@RequestBody Food food) {
        return foodService.saveFood(food);
    }

    @GetMapping("/foods")
    public List<Food> findAllFoods() {
        return foodService.findAllFoods();
    }

    @GetMapping("/foods/{id}")
    public Optional<Food> findFoodById(@PathVariable Long id) {
        return foodService.findFoodById(id);
    }

    @DeleteMapping("foods/{id}")
    public String deleteFood(@PathVariable Long id) {
        return foodService.deleteFood(id);
    }
}
