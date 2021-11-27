package com.dm4nk.recipeproject.converters;

import com.dm4nk.recipeproject.commands.RecipeCommand;
import com.dm4nk.recipeproject.domain.Category;
import com.dm4nk.recipeproject.domain.Ingredient;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    CategoryCommandToCategory categoryConverter;
    IngredientCommandToIngredient ingredientConverter;
    NotesCommandToNotes notesConverter;

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if (source == null) return null;

        Set<Category> categories = new HashSet<>();
        source.getCategories().forEach(categoryCommand -> categories.add(categoryConverter.convert(categoryCommand)));

        Set<Ingredient> ingredientSet = new HashSet<>();
        source.getIngredients().forEach(ingredientCommand -> ingredientSet.add(ingredientConverter.convert(ingredientCommand)));

        return Recipe.builder()
                .id(source.getId())
                .cookTime(source.getCookTime())
                .prepTime(source.getPrepTime())
                .description(source.getDescription())
                .difficulty(source.getDifficulty())
                .directions(source.getDirections())
                .servings(source.getServings())
                .source(source.getSource())
                .url(source.getUrl())
                .notes(notesConverter.convert(source.getNotesCommand()))
                .categories(categories)
                .ingredients(ingredientSet)
                .build();
    }
}
