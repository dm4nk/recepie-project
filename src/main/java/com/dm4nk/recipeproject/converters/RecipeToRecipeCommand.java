package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.CategoryCommand;
import com.dm4nk.recipeproject.commands.IngredientCommand;
import com.dm4nk.recipeproject.commands.RecipeCommand;
import com.dm4nk.recipeproject.domain.Recipe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import lombok.experimental.FieldDefaults;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    CategoryToCategoryCommand categoryConverter;
    IngredientToIngredientCommand ingredientConverter;
    NotesToNotesCommand notesConverter;

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {

        if (source == null) return null;

        Set<CategoryCommand> categoryCommandSet = new HashSet<>();
        source.getCategories().forEach(category -> categoryCommandSet.add(categoryConverter.convert(category)));

        Set<IngredientCommand> ingredientCommandSet = new HashSet<>();
        source.getIngredients().forEach(ingredient -> ingredientCommandSet.add(ingredientConverter.convert(ingredient)));

        return RecipeCommand.builder()
                .id(source.getId())
                .cookTime(source.getCookTime())
                .prepTime(source.getPrepTime())
                .description(source.getDescription())
                .difficulty(source.getDifficulty())
                .directions(source.getDirections())
                .image(source.getImage())
                .servings(source.getServings())
                .source(source.getSource())
                .url(source.getUrl())
                .notesCommand(notesConverter.convert(source.getNotes()))
                .categories(categoryCommandSet)
                .ingredients(ingredientCommandSet)
                .build();
    }
}
