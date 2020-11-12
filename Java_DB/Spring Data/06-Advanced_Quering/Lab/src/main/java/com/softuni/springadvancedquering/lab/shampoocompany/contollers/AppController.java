package com.softuni.springadvancedquering.lab.shampoocompany.contollers;

import com.softuni.springadvancedquering.lab.shampoocompany.entities.Ingredient;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Label;
import com.softuni.springadvancedquering.lab.shampoocompany.entities.Shampoo;
import com.softuni.springadvancedquering.lab.shampoocompany.enums.Size;
import com.softuni.springadvancedquering.lab.shampoocompany.io.InputReader;
import com.softuni.springadvancedquering.lab.shampoocompany.messages.ExceptionMessages;
import com.softuni.springadvancedquering.lab.shampoocompany.messages.Messages;
import com.softuni.springadvancedquering.lab.shampoocompany.services.IngredientService;
import com.softuni.springadvancedquering.lab.shampoocompany.services.LabelService;
import com.softuni.springadvancedquering.lab.shampoocompany.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class AppController implements CommandLineRunner {

    private final ShampooService shampooService;
    private final LabelService labelService;
    private final IngredientService ingredientService;
    private final InputReader reader;

    @Autowired
    public AppController(ShampooService shampooService, LabelService labelService,
                         IngredientService ingredientService, InputReader reader) {
        this.shampooService = shampooService;
        this.labelService = labelService;
        this.ingredientService = ingredientService;
        this.reader = reader;
    }

    @Override
    public void run(String... args) throws Exception {

//        this.selectShampoosBySize();
//        this.selectBySizeOrLabelId();
//        this.selectByPriceOrderDesc();
//        this.selectIngredientsStartsWith();
//        this.selectIngredientsByListOfNames();
//        this.countAllShampoosWithPriceLessThan();
//        this.selectShampoosByIngredients();
//        this.selectShampoosByIngredientsCountLessThan();
//        this.deleteIngredientByName();
//        this.updateIngredientsPriceBy10Percentage();
        this.increaseIngredientsPriceInGivenList();

    }

    private void selectShampoosBySize() {
        System.out.println(Messages.TASK_01_CONDITION);
        Size size = this.getSize();
        List<Shampoo> getAllBySize = this.shampooService.getAllBySize(size);
        System.out.println(this.shampooService.getShampoosBrandSizeAndPrice(getAllBySize));
    }

    private void selectBySizeOrLabelId() {
        System.out.println(Messages.TASK_02_CONDITION);
        Size size = this.getSize();
        Label label = this.getLabel();
        List<Shampoo> allBySizeOrLabel = this.shampooService.getAllBySizeOrLabel(size, label);
        System.out.println(this.shampooService.getShampoosBrandSizeAndPrice(allBySizeOrLabel));
    }

    private void selectByPriceOrderDesc() {
        System.out.println(Messages.TASK_03_CONDITION);
        BigDecimal price = this.getPrice();
        List<Shampoo> allByPriceGreaterThanOrderByPrice =
                this.shampooService.getAllByPriceGreaterThanOrderByPrice(price);
        System.out.println(this.shampooService.getShampoosBrandSizeAndPrice(allByPriceGreaterThanOrderByPrice));

    }

    private void selectIngredientsStartsWith() {
        System.out.println(Messages.TASK_04_CONDITION);
        String startsWith = this.getName();
        List<Ingredient> allByNameStartsWith = this.ingredientService.getAllByNameStartsWith(startsWith);
        System.out.println(this.ingredientService.getIngredientNames(allByNameStartsWith));
    }

    private void selectIngredientsByListOfNames() {
        System.out.println(Messages.TASK_05_CONDITION);
        Collection<String> names = new ArrayList<>();
        names.add("Lavender");
        names.add("Herbs");
        names.add("Apple");
        List<Ingredient> allByNameInOderByPrice = this.ingredientService.getAllByNameInOderByPrice(names);
        System.out.println(this.ingredientService.getIngredientNames(allByNameInOderByPrice));
    }

    private void countAllShampoosWithPriceLessThan() {
        System.out.println(Messages.TASK_06_CONDITION);
        BigDecimal price = getPrice();
        System.out.println(this.shampooService.countAllByPriceLessThan(price));
    }

    private void selectShampoosByIngredients() {
        System.out.println(Messages.TASK_07_CONDITION);
        Collection<Ingredient> ingredients = new LinkedHashSet<>();
        Ingredient ingredient1 = this.ingredientService.getByName("Berry");
        Ingredient ingredient2 = this.ingredientService.getByName("Mineral-Collagen");
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        List<Shampoo> allByIngredientsIn = this.shampooService.getAllByIngredientsIn(ingredients);
        System.out.println(this.shampooService.getNamesOfShampoos(allByIngredientsIn));
    }

    private void selectShampoosByIngredientsCountLessThan() {
        System.out.println(Messages.TASK_08_CONDITION);
        int ingredientCount = getSizeOfIngredients();
        List<Shampoo> allByIngredientsCountLessThan =
                this.shampooService.getAllByIngredientsCountLessThan(ingredientCount);
        System.out.println(this.shampooService.getNamesOfShampoos(allByIngredientsCountLessThan));
    }

    public void deleteIngredientByName() {
        System.out.println(Messages.TASK_09_CONDITION);
        String name = "Apple";
        Ingredient ingredientToDelete = this.ingredientService.getByName(name);
        this.shampooService.deleteIngredientsFromShampoos(ingredientToDelete);
        this.ingredientService.deleteIngredientByName(name);
    }

    private void updateIngredientsPriceBy10Percentage(){
        System.out.println(Messages.TASK_10_CONDITION);
        this.ingredientService.findAll().forEach(System.out::println);
        this.ingredientService.updateAllPricesWithPercentage(new BigDecimal("1.1"));
        this.ingredientService.findAll().forEach(System.out::println);
    }

    private void increaseIngredientsPriceInGivenList(){
        System.out.println(Messages.TASK_11_CONDITION);
        Collection<String> ingredients = new ArrayList<>();
        ingredients.add("Apple");
        ingredients.add("Nettle");
        ingredients.add("Macadamia Oil");
        this.ingredientService.increasePriceOfIngredientsInGivenCollection(ingredients);

    }

    private Size getSize() {
        System.out.println(Messages.ENTER_SIZE);
        String input = reader.readLine();
        Size size = null;
        switch (input) {
            case "SMALL":
                size = Size.SMALL;
                break;
            case "MEDIUM":
                size = Size.MEDIUM;
                break;
            case "LARGE":
                size = Size.LARGE;
                break;
            default:
                System.out.println(ExceptionMessages.INVALID_SIZE);
                return null;
        }
        return size;
    }

    private Label getLabel() {
        System.out.println(Messages.ENTER_LABEL_ID);
        Long labelId = Long.parseLong(reader.readLine());
        return this.labelService.getLabelById(labelId);
    }

    private BigDecimal getPrice() {
        System.out.println(Messages.ENTER_PRICE);
        String input = this.reader.readLine();
        return new BigDecimal(input);
    }

    private String getName() {
        System.out.println(Messages.ENTER_NAME);
        return reader.readLine();
    }

    private int getSizeOfIngredients() {
        System.out.println(Messages.ENTER_SIZE_OF_INGREDIENTS);
        return Integer.parseInt(reader.readLine());
    }

}
