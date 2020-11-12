package com.softuni.exercice.booksystem.services.impl;

import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Category;
import com.softuni.exercice.booksystem.repositories.CategoryRepository;
import com.softuni.exercice.booksystem.services.CategoryService;
import com.softuni.exercice.booksystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() {
        String[] strings = fileUtil.readFileContent(GlobalConstants.CATEGORIES_PATH);
        for (String categoryName : strings) {
            Category category = new Category();
            category.setName(categoryName);
            this.categoryRepository.saveAndFlush(category);
        }
    }
    @Override
    public Set<Category> getSetOfRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();
        Random random = new Random();
        int randomNumberOfCategories = random.nextInt((int)this.categoryRepository.count());
        for (int i = 0; i < randomNumberOfCategories ; i++) {
            int id = random.nextInt((int)this.categoryRepository.count()) + 1;
            Category category = this.categoryRepository.findById((long)id).get();
            categories.add(category);
        }
        return categories;
    }
}
