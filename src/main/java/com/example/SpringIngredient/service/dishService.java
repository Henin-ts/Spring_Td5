package com.example.SpringIngredient.service;


import com.example.SpringIngredient.dto.DishRequest;
import com.example.SpringIngredient.entity.dish;
import com.example.SpringIngredient.entity.dishTypeEnum;
import com.example.SpringIngredient.repository.dishRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class dishService {

     private final dishRepository dishRepository;

     public List<dish>  findAllDish(){
         return dishRepository.findAll();
     }


    public List<dish> createDishes(List<DishRequest> requests) {

        List<dish> result = new ArrayList<>();

        for (DishRequest req : requests) {

            if (dishRepository.existsByName(req.getName())) {
                throw new RuntimeException(
                        "Dish.name=" + req.getName() + " already exists"
                );
            }

            dish dish = new dish();
            dish.setName(req.getName());

            // ⚠️ conversion String → Enum
            dish.setDishType(dishTypeEnum.valueOf(req.getName()));

            dish.setPrice(req.getPrice());

            result.add(dishRepository.save(dish));
        }

        return result;
    }

    public List<dish> getFilteredDishes(Double priceUnder, Double priceOver, String name) {
        return dishRepository.filterDishes(priceUnder, priceOver, name);
    }
}
