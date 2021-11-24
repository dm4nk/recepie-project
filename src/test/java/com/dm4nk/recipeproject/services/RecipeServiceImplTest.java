package com.dm4nk.recipeproject.services;

import com.dm4nk.recipeproject.domain.Recipe;
import com.dm4nk.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
        HashSet<Recipe> data = new HashSet<>();
        data.add(recipe);
        when(recipeRepository.findAll()).thenReturn(data);
    }

    @Test
    void getRecipe() {

        Set<Recipe> recipeSet = recipeService.getRecipe();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }
}