package com.example.SpringIngredient.service;


import com.example.SpringIngredient.entity.ingredient;
import com.example.SpringIngredient.repository.dishRepository;
import com.example.SpringIngredient.repository.ingredientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter

public class ingredientService {

    private final ingredientRepository ingredientRepository;

    public List<ingredient> findAllIngredients(){
        return ingredientRepository.findAll();
    }
    public ingredient findIngredientById(Integer id){
        return ingredientRepository.findById(id).orElseThrow(()->new EntityNotFoundException("ingredient n'ont trouvé"));
    }

}
