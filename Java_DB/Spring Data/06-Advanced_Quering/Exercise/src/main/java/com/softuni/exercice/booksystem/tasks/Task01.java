package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.enums.AgeRestriction;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.util.stream.Collectors;

public class Task01 {

    private final BookRepository bookRepository;

    public Task01(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(String input){
        System.out.println(Conditions.TASK_01_CONDITION);
        AgeRestriction ageRestriction = getAgeRestriction(input);
        if (ageRestriction == null){
            System.out.println("Invalid age restriction!");
        }else {
            System.out.println(bookRepository
                    .getAllByAgeRestriction(ageRestriction)
                    .stream()
                    .map(Book::getTitle)
                    .collect(Collectors.joining(System.lineSeparator())));
        }
    }

    private  AgeRestriction getAgeRestriction(String ageRestrictionName ){
        System.out.printf("%s\n%s\n",
                ageRestrictionName,
                GlobalConstants.DELIMITER);
        AgeRestriction ageRestriction = null;
        switch (ageRestrictionName.toUpperCase()) {
            case "MINOR":
                ageRestriction = AgeRestriction.MINOR;
                break;
            case "TEEN":
                ageRestriction = AgeRestriction.TEEN;
                break;
            case "ADULT":
                ageRestriction = AgeRestriction.ADULT;
                break;
        }
        return ageRestriction;
    }
}
