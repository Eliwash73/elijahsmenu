package com.ejwashin.elijahsmenu.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String recipeLink;
    
    @Lob
    private String ingredients;
    
    @Lob
    private String directions;

    @Column(unique = true)
    private String slug;
}
