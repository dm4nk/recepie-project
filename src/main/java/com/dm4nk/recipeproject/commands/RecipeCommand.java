package com.dm4nk.recipeproject.commands;

import com.dm4nk.recipeproject.domain.Difficulty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeCommand {
    Long id;
    String description;
    Integer prepTime;
    Integer cookTime;
    Integer servings;
    String source;
    String url;
    String directions;
    Set<IngredientCommand> ingredients = new HashSet<>();
    Difficulty difficulty;
    NotesCommand notesCommand;
    Set<CategoryCommand> categories = new HashSet<>();
}
