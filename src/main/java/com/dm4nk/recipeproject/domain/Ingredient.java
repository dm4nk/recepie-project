package com.dm4nk.recipeproject.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    BigDecimal amount;
    @ManyToOne
    Recipe recipe;
    @OneToOne(fetch = FetchType.EAGER)
    UnitOfMeasure uom;
}
