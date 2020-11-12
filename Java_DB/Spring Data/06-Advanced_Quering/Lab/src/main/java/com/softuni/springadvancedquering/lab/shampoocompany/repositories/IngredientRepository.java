package com.softuni.springadvancedquering.lab.shampoocompany.repositories;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {

    List<Ingredient> getAllByNameStartingWith(String name);

    List<Ingredient> getAllByNameInOrderByPrice(Collection<String> name);

    Ingredient getByName(String name);

    @Modifying
    @Transactional
    void deleteIngredientByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :percentage")
    void updateAllPricesWithPercentage(BigDecimal percentage);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :percentage WHERE i.name IN :names")
    public void increasePriceOfIngredientsInGivenCollection(@Param("percentage") BigDecimal percentage,
                                                            @Param("names") Collection<String> names);

}
