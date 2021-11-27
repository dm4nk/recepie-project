package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.CategoryCommand;
import com.dm4nk.recipeproject.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        return source == null ? null : Category.builder()
                .id(source.getId())
                .description(source.getDescription())
                .build();
    }
}
