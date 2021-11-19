package com.dm4nk.recipeproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String description;
    @NonNull
    BigDecimal amount;
    @ManyToOne
    Recipe recipe;
    @NonNull
    @OneToOne(fetch = FetchType.EAGER)
    UnitOfMeasure uom;
}
