package com.softuni.springadvancedquering.lab.shampoocompany.services.impl;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;
import com.softuni.springadvancedquering.lab.shampoocompany.repositories.IngredientRepository;
import com.softuni.springadvancedquering.lab.shampoocompany.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllByNameStartsWith(String name) {
        return this.ingredientRepository.getAllByNameStartingWith(name);
    }

    @Override
    public String getIngredientNames(List<Ingredient> ingredients) {
        return ingredients
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public List<Ingredient> getAllByNameInOderByPrice(Collection<String> names) {
        return this.ingredientRepository.getAllByNameInOrderByPrice(names);
    }

    @Override
    public Ingredient getByName(String name) {
        return this.ingredientRepository.getByName(name);
    }

    @Override
    @Transactional
    public void deleteIngredientByName(String name) {
        this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public void updateAllPricesWithPercentage(BigDecimal percentage) {
        this.ingredientRepository.updateAllPricesWithPercentage(percentage);
    }

    @Override
    public List<Ingredient> findAll() {
        return this.ingredientRepository.findAll();
    }

    @Override
    public void increasePriceOfIngredientsInGivenCollection(Collection<String> names) {
        BigDecimal percentage = new BigDecimal("1.1");
        this.ingredientRepository.increasePriceOfIngredientsInGivenCollection(percentage,names);
    }
}
