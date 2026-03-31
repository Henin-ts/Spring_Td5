package com.example.SpringIngredient.controller;


import com.example.SpringIngredient.entity.dish;
import com.example.SpringIngredient.entity.ingredient;
import com.example.SpringIngredient.repository.dishRepository;
import com.example.SpringIngredient.repository.ingredientRepository;
import com.example.SpringIngredient.service.dishService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/dishes")
public class dishController {

    private dishService dishService;

    @GetMapping
    public List<dish> findAllDish(){
        return dishService.findAllDish();
    }
    @RestController
    @RequestMapping("/dishes")
    public class DishController {

        @Autowired
        private dishRepository dishRepository;

        @Autowired
        private ingredientRepository ingredientRepository;

        @PutMapping("/{id}/ingredients")
        public ResponseEntity<?> updateDishIngredients(
                @PathVariable Integer id,
                @RequestBody(required = false) List<ingredient> ingredientsRequest) {

            if (ingredientsRequest == null) {
                return ResponseEntity
                        .badRequest()
                        .body("Request body is required and cannot be null.");
            }

            Optional<dish> dishOpt = dishRepository.findById(id);
            if (dishOpt.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Dish.id=" + id + " is not found");
            }

            dish dish = dishOpt.get();

            List<ingredient> validIngredients = ingredientsRequest.stream()
                    .map(ingredient -> ingredientRepository.findById(ingredient.getId()))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());

            dish.setIngredients(validIngredients);

            dishRepository.save(dish);

            return ResponseEntity.ok(dish);
        }
    }
}
