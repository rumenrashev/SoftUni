package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.repositories.BookRepository;

public class Task11 {

    private final BookRepository bookRepository;

    public Task11(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void solve(String title){
        System.out.println(Conditions.TASK_11_CONDITION);
        System.out.println(title + System.lineSeparator() + GlobalConstants.DELIMITER);
        String bookInfo = this.bookRepository.getBookInfoByTitle(title);
        System.out.println(bookInfo.replace(","," "));
    }
}
