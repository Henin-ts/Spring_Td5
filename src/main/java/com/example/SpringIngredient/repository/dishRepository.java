package com.example.SpringIngredient.repository;


import com.example.SpringIngredient.entity.dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface dishRepository extends JpaRepository<dish,Integer> {

    public List<dish> findById(int id);

}
