package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;

import java.util.stream.Collectors;

public class Task06 {

    private final AuthorRepository authorRepository;


    public Task06(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void solve(String endsWith){
        System.out.println(Conditions.Task_06_CONDITION);
        System.out.println(endsWith + System.lineSeparator() + GlobalConstants.DELIMITER);
        System.out.println(this.authorRepository
                .getAllByFirstNameEndingWith(endsWith)
                .stream()
                .map(a -> String.format("%s %s", a.getFirstName(), a.getLastName()))
                .collect(Collectors.joining(System.lineSeparator())));

    }
}
