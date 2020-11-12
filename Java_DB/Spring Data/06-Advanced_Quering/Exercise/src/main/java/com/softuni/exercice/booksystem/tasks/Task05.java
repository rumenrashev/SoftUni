package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class Task05 {

    private final BookRepository bookRepository;


    public Task05(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(String releaseDate){
        System.out.println(Conditions.TASK_05_CONDITION);
        System.out.println(releaseDate + System.lineSeparator() + GlobalConstants.DELIMITER);
        String[] split = releaseDate.split("-");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        LocalDate date = LocalDate.of(year,month,day);
        System.out.println(this.bookRepository.getAllByReleaseDateLessThan(date)
                .stream()
                .map(b -> String.format("%s %s %s",
                        b.getTitle(),
                        b.getEditionType(),
                        b.getPrice()))
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
