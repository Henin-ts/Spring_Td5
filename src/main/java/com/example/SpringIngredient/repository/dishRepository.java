package com.example.SpringIngredient.repository;


import com.example.SpringIngredient.entity.dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface dishRepository extends JpaRepository<dish,Integer> {

    public List<dish> findById(int id);

    boolean existsByName(String name);

    @Query("""
        SELECT d FROM dish d
        WHERE (:priceUnder IS NULL OR d.price < :priceUnder)
        AND (:priceOver IS NULL OR d.price > :priceOver)
        AND (:name IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')))
    """)

    List<dish> filterDishes(Double priceUnder, Double priceOver, String name);

}
