package com.softuni.exercice.booksystem.services.impl;

import com.softuni.exercice.booksystem.constants.GlobalConstants;
import com.softuni.exercice.booksystem.entities.Author;
import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.entities.Category;
import com.softuni.exercice.booksystem.enums.AgeRestriction;
import com.softuni.exercice.booksystem.enums.EditionType;
import com.softuni.exercice.booksystem.repositories.BookRepository;
import com.softuni.exercice.booksystem.services.AuthorService;
import com.softuni.exercice.booksystem.services.BookService;
import com.softuni.exercice.booksystem.services.CategoryService;
import com.softuni.exercice.booksystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService,
                           CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() {
        String[] books = this.fileUtil.readFileContent(GlobalConstants.BOOKS_PATH);
        for (String line : books) {
            String[] data = line.split("\\s+");
            Author author = this.authorService.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            LocalDate releaseDate = this.getReleaseDate(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            Set<Category> categories = categoryService.getSetOfRandomCategories();
            String title = titleBuilder.toString().trim();
            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);
            this.bookRepository.saveAndFlush(book);
        }
    }
    private LocalDate getReleaseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
