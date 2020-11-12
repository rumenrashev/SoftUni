package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.util.List;

public class Task09 {

    private final BookRepository bookRepository;

    public Task09(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(int length){
        System.out.println(Conditions.TASK_09_CONDITION);
        System.out.println(length + System.lineSeparator() + GlobalConstants.DELIMITER);
        int count = this.bookRepository.countAllByTitleWithSymbolsMoreThan(length);
        System.out.println(count);
    }
}
