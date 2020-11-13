package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;

public class Task14 {

    private final AuthorRepository authorRepository;

    public Task14(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void solve(String fullName){
        System.out.println(Conditions.TASK_14_CONDITION);
        System.out.println(fullName + System.lineSeparator() + GlobalConstants.DELIMITER);
        int amountOfBooks = this.authorRepository.getTotalAmountOfBooksByAuthorName(fullName);
        System.out.printf("%s has written %d books%n", fullName, amountOfBooks);
    }
}
