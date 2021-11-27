package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.IngredientCommand;
import com.dm4nk.recipeproject.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        return source == null ? null : IngredientCommand.builder()
                .id(source.getId())
                .description(source.getDescription())
                .amount(source.getAmount())
                .unitOfMeasure(uomConverter.convert(source.getUom()))
                .build();
    }
}
