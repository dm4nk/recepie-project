package com.dm4nk.recipeproject.controllers;

import com.dm4nk.recipeproject.services.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class IndexController {

    RecipeService recipeService;

    @RequestMapping({"", "/", "/index"})
    private String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipe());

        return "index";
    }
}
