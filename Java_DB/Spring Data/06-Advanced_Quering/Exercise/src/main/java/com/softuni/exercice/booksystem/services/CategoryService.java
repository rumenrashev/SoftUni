package com.softuni.exercice.booksystem.services;


import com.softuni.exercice.booksystem.entities.Category;

import java.util.Set;

public interface CategoryService {

    void seedCategories();

    Set<Category> getSetOfRandomCategories();
}
