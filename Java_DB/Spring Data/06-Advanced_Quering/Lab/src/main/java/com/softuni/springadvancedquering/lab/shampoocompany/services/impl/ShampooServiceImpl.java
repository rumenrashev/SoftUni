package com.softuni.springadvancedquering.lab.shampoocompany.services.impl;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Shampoo;
import com.softuni.springadvancedquering.lab.shampoocompany.enums.Size;
import com.softuni.springadvancedquering.lab.shampoocompany.repositories.ShampooRepository;
import com.softuni.springadvancedquering.lab.shampoocompany.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public String getShampoosBrandSizeAndPrice(List<Shampoo> shampoos) {
        return shampoos
                .stream()
                .map(s -> String.format("%s %s %slv.",s.getBrand(),s.getSize(),s.getPrice()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public List<Shampoo> getAllBySize(Size size) {
        return this.shampooRepository
                .getAllBySize(size);
    }

    @Override
    public List<Shampoo> getAllBySizeOrLabel(Size size, Label label) {
        return this.shampooRepository.getAllBySizeOrLabelOrderByPrice(size,label);
    }

    @Override
    public List<Shampoo> getAllByPriceGreaterThanOrderByPrice(BigDecimal price) {
        return this.shampooRepository.getAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Long countAllByPriceLessThan(BigDecimal price) {
        return this.shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<Shampoo> getAllByIngredientsIn(Collection<Ingredient> ingredients) {
        return this.shampooRepository.getAllByIngredientsIn(ingredients);
    }

    @Override
    public String getNamesOfShampoos(List<Shampoo> shampoos) {
        return shampoos
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public List<Shampoo> getAllByIngredientsCountLessThan(int size) {
        return this.shampooRepository.getAllByIngredientsCountLessThan(size);
    }

    @Override
    public List<Shampoo> getAll() {
        return this.shampooRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteIngredientsFromShampoos(Ingredient ingredient) {
        List<Shampoo> all = this.shampooRepository.findAll();
        all.forEach(s-> s.getIngredients().remove(ingredient));
        all = this.shampooRepository.findAll();
        System.out.println();
    }
}
