package com.dm4nk.recipeproject.commands;

import com.dm4nk.recipeproject.domain.Difficulty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
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

    @NotBlank
    @Size(min = 3, max = 255)
    String description;

    @Min(1)
    @Max(999)
    @NotNull
    Integer prepTime;

    @Min(1)
    @Max(999)
    @NotNull
    Integer cookTime;

    @Min(1)
    @Max(100)
    @NotNull
    Integer servings;
    String source;

    @URL
    String url;

    @NotBlank
    String directions;

    Byte[] image;
    Set<IngredientCommand> ingredients = new HashSet<>();
    Difficulty difficulty;
    NotesCommand notesCommand;
    Set<CategoryCommand> categories = new HashSet<>();
}
