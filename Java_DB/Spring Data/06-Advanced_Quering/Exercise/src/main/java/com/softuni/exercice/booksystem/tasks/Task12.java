package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task12 {

    private final BookRepository bookRepository;

    public Task12(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(String dateInput , int copies){
        System.out.println(Conditions.TASK_12_CONDITION);
        System.out.println(dateInput);
        System.out.println(copies);
        System.out.println(GlobalConstants.DELIMITER);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd LLL yyyy");
        LocalDate date = LocalDate.parse(dateInput,dtf);
        List<Book> books = this.bookRepository.getAllByReleaseDateGreaterThan(date);
        int count = this.bookRepository.increaseCopiesInGivenList(copies, books);
        System.out.println(count * copies);
    }
}
