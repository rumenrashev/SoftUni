package com.softuni.springadvancedquering.lab.shampoocompany.repositories;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Shampoo;
import com.softuni.springadvancedquering.lab.shampoocompany.enums.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo,Long> {

    List<Shampoo> getAllBySize(Size size);

    List<Shampoo> getAllBySizeOrLabelOrderByPrice(Size size, Label label);

    List<Shampoo> getAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Long countAllByPriceLessThan(BigDecimal price);

    List<Shampoo> getAllByIngredientsIn(Collection<Ingredient> ingredients);

    @Query("SELECT s FROM Shampoo s WHERE s.ingredients.size < :size")
    List<Shampoo> getAllByIngredientsCountLessThan(int size);



}
