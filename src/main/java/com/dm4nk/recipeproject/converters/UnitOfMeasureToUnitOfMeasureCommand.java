package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.UnitOfMeasureCommand;
import com.dm4nk.recipeproject.domain.UnitOfMeasure;
import com.sun.istack.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        return source == null ? null : UnitOfMeasureCommand.builder()
                .id(source.getId())
                .description(source.getDescription())
                .build();
    }
}
