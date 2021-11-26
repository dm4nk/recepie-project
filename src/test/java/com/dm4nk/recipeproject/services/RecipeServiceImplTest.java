package com.dm4nk.recipeproject.services;

import com.dm4nk.recipeproject.domain.Recipe;
import com.dm4nk.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository);

        recipe = new Recipe();
        recipe.setId(1L);
        HashSet<Recipe> data = new HashSet<>();
        data.add(recipe);
        when(recipeRepository.findAll()).thenReturn(data);
    }

    @Test
    void getRecipeByIdTest() {
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(1L)).thenReturn(recipeOptional);

        Recipe actualRecipe = recipeService.findById(1L);

        assertNotNull(actualRecipe);
        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipe() {

        Set<Recipe> recipeSet = recipeService.getRecipe();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}