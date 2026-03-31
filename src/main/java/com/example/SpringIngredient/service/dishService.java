package com.example.SpringIngredient.service;


import com.example.SpringIngredient.entity.dish;
import com.example.SpringIngredient.repository.dishRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class dishService {

     private final dishRepository dishRepository;

     public List<dish>  findAllDish(){
         return dishRepository.findAll();
     }
}
