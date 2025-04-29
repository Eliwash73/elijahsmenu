package com.ejwashin.elijahsmenu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejwashin.elijahsmenu.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByTitle(String title);
    Recipe findBySlug(String slug);

} 
