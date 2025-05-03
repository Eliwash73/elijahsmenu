package com.ejwashin.elijahsmenu.contoller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import com.ejwashin.elijahsmenu.entity.Recipe;
import com.ejwashin.elijahsmenu.service.RecipeService;

@Controller
public class RecipeBookController {
    // This class will handle HTTP requests related to recipes
    // It will use the RecipeService to perform CRUD operations on recipes

    private final RecipeService recipeService;
    
    // Constructor injection for the RecipeService
    public RecipeBookController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    // Endpoints for creating, reading, updating, and deleting recipes
    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/recipes")
    public String getAllRecipes(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes"; 
    }

    @GetMapping("/recipes/new")
    public String showRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe_form"; // this looks for a template called recipe_form.html
    }   
    @GetMapping("/recipes/newmd")
    public String showRecipeMarkdownForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe_form_markdown"; // this looks for a template called recipe_form.html
    }

    @PostMapping("/recipes/new")
    public String addRecipe(@ModelAttribute Recipe recipe) {
        String slug = recipeService.generateSlug(recipe.getTitle());
        recipe.setSlug(slug);
        recipeService.addRecipe(recipe);
        return "redirect:/recipes";
    }
    @PostMapping("/recipes/newmd")
    public String addRecipeMarkdown(@ModelAttribute Recipe recipe) {
        String slug = recipeService.generateSlug(recipe.getTitle());
        recipe.setSlug(slug);
        recipeService.addRecipe(recipe);
        return "redirect:/recipes";
    }
    

    @GetMapping("/recipes/{slug}")
    public String getRecipeBySlug(@PathVariable String slug, Model model) {
        Recipe recipe = recipeService.getRecipeBySlug(slug);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "recipe_detail";
        } else {
            return "redirect:/recipes"; // Redirect to the recipes page if not found
        }
    }
    
    @GetMapping("/recipes/edit/{slug}")
    public String showEditRecipeForm(@PathVariable String slug, Model model) {
        Recipe recipe = recipeService.getRecipeBySlug(slug);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "recipe_edit"; // this looks for a template called recipe_edit.html
        } else {
            return "redirect:/recipes"; // Redirect to the recipes page if not found
        }
    }

    @PostMapping("/recipes/edit/{slug}")
    public String updateRecipe(@PathVariable String slug, @ModelAttribute Recipe recipe) {
        recipe.setSlug(slug); // Set the slug to the existing one
        recipeService.updateRecipe(recipe);
        return "redirect:/recipes/" + slug; // Redirect to the updated recipe detail page
    }
        
}
