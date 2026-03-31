package com.example.SpringIngredient.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="ingredient")
@Getter
@Setter
public class ingredient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Double price;
    @Enumerated(EnumType.STRING)
    categoryEnum  category;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="id_dish")
    dish dish;
    }
