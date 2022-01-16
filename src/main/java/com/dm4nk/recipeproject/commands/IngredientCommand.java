package com.dm4nk.recipeproject.commands;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientCommand {
    Long id;
    Long recipeId;
    @NotBlank
    String description;
    @Min(0)
    @NotNull
    BigDecimal amount;
    UnitOfMeasureCommand uom;
}
