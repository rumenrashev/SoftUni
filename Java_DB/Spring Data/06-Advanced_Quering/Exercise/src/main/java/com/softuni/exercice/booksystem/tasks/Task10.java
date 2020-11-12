package com.softuni.exercice.booksystem.tasks;

import com.softuni.exercice.booksystem.constants.Conditions;
import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Author;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;

public class Task10 {

    private final AuthorRepository authorRepository;

    public Task10(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void solve(String fullName){
        System.out.println(Conditions.TASK_10_CONDITION);
        System.out.println(fullName + System.lineSeparator() + GlobalConstants.DELIMITER);
        String[] split = fullName.split("\\s+");
        String firstName = split[0];
        String lastName = split[1];
        Author author = this.authorRepository.findByFirstNameEqualsAndLastNameEquals(firstName, lastName);
        int countCopies = 0;
        for (Book book : author.getBooks()) {
            countCopies += book.getCopies();
        }
        System.out.println(String.format("%s - %d",fullName,countCopies));
    }
}
