package com.dm4nk.recipeproject.services;

import com.dm4nk.recipeproject.commands.RecipeCommand;
import com.dm4nk.recipeproject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipe();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);
}
