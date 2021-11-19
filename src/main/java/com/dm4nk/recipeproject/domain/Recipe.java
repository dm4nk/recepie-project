package com.dm4nk.recipeproject.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    String description;
    @NonNull
    Integer prepTime;
    @NonNull
    Integer cookTime;
    @NonNull
    Integer servings;
    @NonNull
    String source;
    @NonNull
    String url;
    @NonNull
    @Lob
    String directions;
    @NonNull
    @Lob
    Byte[] image;
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    Notes notes;
    @NonNull
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<>();
    @NonNull
    @Enumerated(value = EnumType.STRING)
    Difficulty difficulty;
    @NonNull
    @ManyToMany
    @JoinTable(name = "recipe_category",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
