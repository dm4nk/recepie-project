package com.dm4nk.recipeproject.services;

import com.dm4nk.recipeproject.converters.RecipeCommandToRecipe;
import com.dm4nk.recipeproject.converters.RecipeToRecipeCommand;
import com.dm4nk.recipeproject.domain.Recipe;
import com.dm4nk.recipeproject.exeptions.NotFoundExeption;
import com.dm4nk.recipeproject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @InjectMocks
    RecipeServiceImpl recipeService;
    Recipe recipe;

    @BeforeEach
    void setUp() {
        recipe = new Recipe();
        recipe.setId(1L);
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

    @Test()
    void getRecipeByIdNotFoundTest() {
        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        assertThrows(NotFoundExeption.class, () -> recipeService.findById(1L));
    }

    @Test
    void getRecipe() {
        HashSet<Recipe> data = new HashSet<>();
        data.add(recipe);
        when(recipeRepository.findAll()).thenReturn(data);

        Set<Recipe> recipeSet = recipeService.getRecipe();

        assertEquals(recipeSet.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        Long idToDelete = 2L;
        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(idToDelete);
    }
}