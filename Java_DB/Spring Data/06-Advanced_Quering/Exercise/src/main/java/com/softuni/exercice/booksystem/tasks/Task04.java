package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class Task04 {

    private final BookRepository bookRepository;

    public Task04(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(int year){
        System.out.println(Conditions.TASK_04_CONDITION);
        LocalDate date1 = LocalDate.of(year,1,1);
        LocalDate date2 = LocalDate.of(year,12,3);
        System.out.println(year + System.lineSeparator() + GlobalConstants.DELIMITER);
        System.out.println(this.bookRepository
                .getAllByReleaseDateLessThanOrReleaseDateGreaterThan(date1, date2)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(System.lineSeparator())));

    }
}
