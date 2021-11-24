package com.dm4nk.recipeproject.controllers;

import com.dm4nk.recipeproject.services.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@Slf4j

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class IndexController {

    RecipeService recipeService;

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        log.debug("Getting index page");

        model.addAttribute("recipes", recipeService.getRecipe());

        return "index";
    }
}
