package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Task03 {

    private final BookRepository bookRepository;


    public Task03(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(BigDecimal max , BigDecimal min){
        System.out.println(Conditions.TASK_03_CONDITION);
        System.out.println(this.bookRepository
                .getAllByPriceGreaterThanOrPriceLessThan(max,min)
                .stream()
                .map(b -> String.format("%s - $%s", b.getTitle(), b.getPrice()))
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
