package com.softuni.springadvancedquering.lab.shampoocompany.services;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface IngredientService {

    List<Ingredient> getAllByNameStartsWith(String name);

    String getIngredientNames(List<Ingredient> ingredients);

    List<Ingredient> getAllByNameInOderByPrice(Collection<String> names);

    Ingredient getByName(String name);

    void deleteIngredientByName(String name);

    void updateAllPricesWithPercentage(BigDecimal percentage);

    List<Ingredient> findAll();

    void increasePriceOfIngredientsInGivenCollection(Collection<String> names);
}
