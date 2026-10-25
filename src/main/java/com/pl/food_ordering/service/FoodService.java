package com.pl.food_ordering.service;

import com.pl.food_ordering.entity.Food;
import com.pl.food_ordering.exception.FoodNotFoundException;
import com.pl.food_ordering.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> findAllFoods() { return foodRepository.findAll(); }

    public Optional<Food> findFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public String deleteFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new FoodNotFoundException("Food not found"));
        String name = food.getName();
        foodRepository.deleteById(id);
        return "Food item " + name + " is deleted";
    }
}
