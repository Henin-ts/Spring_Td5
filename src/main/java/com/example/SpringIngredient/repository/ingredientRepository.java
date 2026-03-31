package com.example.SpringIngredient.repository;


import com.example.SpringIngredient.entity.ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ingredientRepository extends JpaRepository<ingredient,Integer> {

}
