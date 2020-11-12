package com.softuni.exercice.booksystem.services.impl;

import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Author;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;
import com.softuni.exercice.booksystem.services.AuthorService;
import com.softuni.exercice.booksystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() {
        String[] authors = this.fileUtil.readFileContent(GlobalConstants.AUTHORS_PATH);
        for (String line : authors) {
            String[] split = line.split("\\s+");
            Author author = new Author();
            String firstName = split[0];
            String lastName = split[1];
            author.setFirstName(firstName);
            author.setLastName(lastName);
            this.authorRepository.saveAndFlush(author);
        }
    }
    @Override
    public int getSize() {
        return (int)this.authorRepository.count();
    }

    @Override
    public Author getRandomAuthor() {
        Random random = new Random();
        long id = random.nextInt(this.getSize()) + 1;
        return this.authorRepository.findById(id).get();
    }
}
