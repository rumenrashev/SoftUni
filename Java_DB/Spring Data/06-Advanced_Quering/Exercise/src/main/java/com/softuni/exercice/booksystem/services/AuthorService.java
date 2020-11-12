package com.softuni.exercice.booksystem.services;

import com.softuni.exercice.booksystem.entities.Author;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface AuthorService {

    void seedAuthors();


    public int getSize();

    public Author getRandomAuthor();
}
