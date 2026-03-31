package com.example.SpringIngredient.controller;


import com.example.SpringIngredient.entity.ingredient;
import com.example.SpringIngredient.service.ingredientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredients")
public class ingredientController {

    private ingredientService ingredientService;

    @GetMapping
    public List<ingredient> findAll(){
        return ingredientService.findAllIngredients();
    }

    @GetMapping("/")
    public ingredient findById(@RequestParam Integer id){
        return ingredientService.findIngredientById(id);
    }
}
