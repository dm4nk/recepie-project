package com.dm4nk.recipeproject.services;

import com.dm4nk.recipeproject.domain.Recipe;
import com.dm4nk.recipeproject.repositories.RecipeRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service

@Slf4j

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RecipeServiceImpl implements RecipeService {

    RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getRecipe() {
        log.debug("Getting service");

        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }
}
