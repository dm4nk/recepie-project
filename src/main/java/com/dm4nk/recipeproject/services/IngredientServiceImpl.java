package com.dm4nk.recipeproject.services;

import com.dm4nk.recipeproject.commands.IngredientCommand;
import com.dm4nk.recipeproject.converters.IngredientCommandToIngredient;
import com.dm4nk.recipeproject.converters.IngredientToIngredientCommand;
import com.dm4nk.recipeproject.domain.Ingredient;
import com.dm4nk.recipeproject.domain.Recipe;
import com.dm4nk.recipeproject.repositories.RecipeRepository;
import com.dm4nk.recipeproject.repositories.UnitOfMeasureRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service

@Slf4j

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class IngredientServiceImpl implements IngredientService {

    IngredientToIngredientCommand ingredientToIngredientCommand;
    IngredientCommandToIngredient ingredientCommandToIngredient;
    RecipeRepository recipeRepository;
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            //todo impl error handling
            log.error("recipe id not found. Id: " + recipeId);
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (ingredientCommandOptional.isEmpty()) {
            //todo impl error handling
            log.error("Ingredient id not found: " + ingredientId);
        }

        return ingredientCommandOptional.get();
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (recipeOptional.isEmpty()) {

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(command);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream().
                    filter(recipeIngredient -> recipeIngredient.getId().equals(command.getId()))
                    .findFirst();

            if (savedIngredientOptional.isEmpty())
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                        .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                        .filter(ingredient -> ingredient.getUom().getId().equals(command.getUom().getId()))
                        .findFirst();

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }
    }
}
