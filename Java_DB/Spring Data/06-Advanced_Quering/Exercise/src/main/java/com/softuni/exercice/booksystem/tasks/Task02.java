package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.enums.EditionType;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.util.stream.Collectors;

public class Task02 {

    private final BookRepository bookRepository;

    public Task02(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(int copies, EditionType editionType){
        System.out.println(Conditions.TASK_02_CONDITION);
        System.out.println(this.bookRepository.getAllByCopiesLessThanAndEditionType(copies,editionType)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
