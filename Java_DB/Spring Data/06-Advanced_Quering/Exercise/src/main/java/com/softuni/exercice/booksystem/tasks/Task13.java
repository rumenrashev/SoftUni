package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;
import com.softuni.exercice.booksystem.repositories.BookRepository;
import com.softuni.exercice.booksystem.repositories.CategoryRepository;

public class Task13 {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public Task13(BookRepository bookRepository, AuthorRepository authorRepository,
                  CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public void solve(int copies){
        System.out.println(Conditions.TASK_13_CONDITION);
        System.out.println(copies + System.lineSeparator() + GlobalConstants.DELIMITER);
        this.bookRepository.removeAllByCopiesLessThan(copies);
    }

}
