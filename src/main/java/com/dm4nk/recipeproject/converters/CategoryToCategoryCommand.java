package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.CategoryCommand;
import com.dm4nk.recipeproject.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {


    @Override
    public CategoryCommand convert(Category source) {
        return source == null ? null : CategoryCommand.builder()
                .id(source.getId())
                .description(source.getDescription())
                .build();
    }
}
