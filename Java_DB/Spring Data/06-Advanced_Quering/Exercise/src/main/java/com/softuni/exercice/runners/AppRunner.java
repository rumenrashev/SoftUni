package com.softuni.exercice.runners;

import com.softuni.exercice.booksystem.enums.EditionType;
import com.softuni.exercice.booksystem.repositories.AuthorRepository;
import com.softuni.exercice.booksystem.repositories.BookRepository;
import com.softuni.exercice.booksystem.services.AuthorService;
import com.softuni.exercice.booksystem.services.BookService;
import com.softuni.exercice.booksystem.services.CategoryService;
import com.softuni.exercice.booksystem.tasks.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class AppRunner implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public AppRunner(AuthorService authorService, CategoryService categoryService,
                     BookService bookService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        authorService.seedAuthors();
        categoryService.seedCategories();
        bookService.seedBooks();
        task01();
        task02();
        task03();
        task04();
        task05();
        task06();
        task07();
        task08();
        task09();
        task10();
        task11();
        task12();
    }

    private void task01() {
        final String input1 = "miNor";
        final String input2 = "teEN";
        Task01 task1 = new Task01(bookRepository);
        task1.solve(input1);
        task1.solve(input2);
    }

    private void task02() {
        final int copies = 5000;
        final EditionType editionType = EditionType.GOLD;
        Task02 task02 = new Task02(bookRepository);
        task02.solve(copies, editionType);
    }

    private void task03() {
        final BigDecimal maxPrice = new BigDecimal(40);
        final BigDecimal minPrice = new BigDecimal(5);
        Task03 task03 = new Task03(bookRepository);
        task03.solve(maxPrice, minPrice);
    }

    private void task04() {
        final int year1 = 2000;
        final int year2 = 1998;
        Task04 task04 = new Task04(bookRepository);
        task04.solve(year1);
        task04.solve(year2);
    }

    private void task05() {
        final String date1 = "12-04-1992";
        final String date2 = "30-12-1989";
        Task05 task05 = new Task05(bookRepository);
        task05.solve(date1);
        task05.solve(date2);
    }

    private void task06() {
        final String endsWith1 = "e";
        final String endsWith2 = "dy";
        Task06 task06 = new Task06(authorRepository);
        task06.solve(endsWith1);
        task06.solve(endsWith2);
    }

    private void task07() {
        final String contains1 = "sK";
        final String contains2 = "WOR";
        Task07 task07 = new Task07(bookRepository);
        task07.solve(contains1);
        task07.solve(contains2);
    }

    public void task08() {
        final String startsWith1 = "Ric";
        final String startsWith2 = "gr";
        Task08 task08 = new Task08(authorRepository);
        task08.solve(startsWith1);
        task08.solve(startsWith2);
    }

    public void task09() {
        final int length1 = 12;
        final int length2 = 40;
        Task09 task09 = new Task09(bookRepository);
        task09.solve(length1);
        task09.solve(length2);
    }

    public void task10() {
        final String fullName = "Randy Graham";
        Task10 task10 = new Task10(authorRepository);
        task10.solve(fullName);
    }

    public void task11() {
        final String title = "Things Fall Apart";
        Task11 task11 = new Task11(bookRepository);
        task11.solve(title);
    }

    private void task12() {
        final String date1 = "12 Oct 2005";
        final int copies1 = 100;
        final String date2 = "06 Jun 2013";
        final int copies2 = 44;
        Task12 task12 = new Task12(bookRepository);
        task12.solve(date1,copies1);
        task12.solve(date2,copies2);
    }
}
