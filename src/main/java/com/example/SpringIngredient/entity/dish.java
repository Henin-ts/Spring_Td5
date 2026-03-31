package com.example.SpringIngredient.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="dish")
@Getter
@Setter

public class dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(name="dish_type")
    @Enumerated(EnumType.STRING)
    dishTypeEnum  dishType;
    Double price;
    @OneToMany(mappedBy = "dish", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<ingredient> ingredients;


}
