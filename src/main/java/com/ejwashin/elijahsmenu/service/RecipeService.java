package com.ejwashin.elijahsmenu.service;

import com.ejwashin.elijahsmenu.entity.Recipe;
import com.ejwashin.elijahsmenu.exception.RecipeNotFoundException;
import com.ejwashin.elijahsmenu.repository.RecipeRepository;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
// This class will handle the business logic related to recipes
public class RecipeService {
    
    RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // This method will return a list of all recipes
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe getRecipeBySlug(String slug) {
        Recipe recipe = recipeRepository.findBySlug(slug);
        if (recipe == null) {
            throw new RecipeNotFoundException("Recipe with slug '" + slug + "' not found");
        }
        return recipe;
    }
    
    public Recipe addRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public String generateSlug(String title) {
        return title.toLowerCase()
                    .replaceAll("[^a-z0-9]+", "-")
                    .replaceAll("^-|-$", "");
    }
    
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

    public Recipe updateRecipe(Recipe recipe) {
        Recipe existingRecipe = recipeRepository.findBySlug(recipe.getSlug());
        existingRecipe.setTitle(recipe.getTitle());
        existingRecipe.setRecipeLink(recipe.getRecipeLink());
        existingRecipe.setIngredients(recipe.getIngredients());
        existingRecipe.setDirections(recipe.getDirections());
        return recipeRepository.save(existingRecipe);
    }
    

}
