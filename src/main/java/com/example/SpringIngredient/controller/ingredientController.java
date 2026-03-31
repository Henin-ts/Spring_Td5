package com.example.SpringIngredient.controller;


import com.example.SpringIngredient.entity.ingredient;
import com.example.SpringIngredient.repository.ingredientRepository;
import com.example.SpringIngredient.service.ingredientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredients")
public class ingredientController {

    private ingredientService ingredientService;
    private ingredientRepository ingredientRepository;

    @GetMapping
    public List<ingredient> findAll(){
        return ingredientService.findAllIngredients();
    }

    @GetMapping("/")
    public ingredient findById(@RequestParam Integer id){
        return ingredientService.findIngredientById(id);
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<?> getIngredientStock(
            @PathVariable Integer id,
            @RequestParam(required = false) String at,
            @RequestParam(required = false) String unit) {


        if (at == null || unit == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Either mandatory query parameter `at` or `unit` is not provided.");
        }

        Optional<ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Ingredient.id=" + id + " is not found");
        }

        ingredient ingredient = optionalIngredient.get();

        double stockValue = 100;
        Map<String, Object> stockResponse = new HashMap<>();
        stockResponse.put("unit", unit);
        stockResponse.put("value", stockValue);

        return ResponseEntity.ok(stockResponse);
    }
}
