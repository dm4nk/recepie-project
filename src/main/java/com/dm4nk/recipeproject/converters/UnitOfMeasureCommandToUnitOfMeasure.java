package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.UnitOfMeasureCommand;
import com.dm4nk.recipeproject.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        return source == null ? null : UnitOfMeasure.builder()
                .id(source.getId())
                .description(source.getDescription())
                .build();
    }
}
