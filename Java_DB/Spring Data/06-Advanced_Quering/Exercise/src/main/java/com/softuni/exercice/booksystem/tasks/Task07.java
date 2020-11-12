package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.util.stream.Collectors;

public class Task07 {

    private final BookRepository bookRepository;

    public Task07(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(String contains){
        System.out.println(Conditions.TASK_07_CONDITION);
        System.out.println(contains + System.lineSeparator() + GlobalConstants.DELIMITER);
        System.out.println(this.bookRepository
                .getAllByTitleContaining(contains)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
