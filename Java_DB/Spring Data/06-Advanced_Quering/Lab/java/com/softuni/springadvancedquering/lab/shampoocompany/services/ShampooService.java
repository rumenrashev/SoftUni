package com.softuni.springadvancedquering.lab.shampoocompany.services;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Shampoo;
import com.softuni.springadvancedquering.lab.shampoocompany.enums.Size;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ShampooService {

    String getShampoosBrandSizeAndPrice(List<Shampoo> shampoos);

    List<Shampoo> getAllBySize(Size size);

    List<Shampoo> getAllBySizeOrLabel(Size size, Label label);

    List<Shampoo> getAllByPriceGreaterThanOrderByPrice(BigDecimal price);

    Long countAllByPriceLessThan(BigDecimal price);

    List<Shampoo> getAllByIngredientsIn(Collection<Ingredient> ingredients);

    String getNamesOfShampoos(List<Shampoo> shampoos);

    List<Shampoo> getAllByIngredientsCountLessThan(int size);

    List<Shampoo> getAll();

    void deleteIngredientsFromShampoos(Ingredient ingredient);
}
