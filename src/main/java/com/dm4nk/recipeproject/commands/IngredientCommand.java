package com.dm4nk.recipeproject.commands;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientCommand {
    Long id;
    String description;
    BigDecimal amount;
    UnitOfMeasureCommand uom;
}
