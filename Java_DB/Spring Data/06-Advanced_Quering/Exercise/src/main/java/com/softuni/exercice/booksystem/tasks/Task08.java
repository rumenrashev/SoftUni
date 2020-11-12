package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Author;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;
import com.softuni.exercice.booksystem.repositories.BookRepository;

import java.util.List;

public class Task08 {

    private final AuthorRepository authorRepository;

    public Task08(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void solve(String startsWith){
        System.out.println(Conditions.TASK_08_CONDITION);
        System.out.println(startsWith + System.lineSeparator() + GlobalConstants.DELIMITER);
        List<Author> authors = this.authorRepository.getAllByLastNameStartsWith(startsWith);
        StringBuilder sb = new StringBuilder();
        for (Author author : authors) {
            for (Book book : author.getBooks()) {
                sb.append(String.format("%s (%s %s)%n",book.getTitle(), author.getFirstName(),author.getLastName()));
            }
        }
        System.out.println(sb.toString());
    }
}
