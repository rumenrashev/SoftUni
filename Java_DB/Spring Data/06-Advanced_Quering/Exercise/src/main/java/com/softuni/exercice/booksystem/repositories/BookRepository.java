package com.softuni.exercice.booksystem.repositories;

import com.softuni.exercice.booksystem.entities.Book;
import com.softuni.exercice.booksystem.enums.AgeRestriction;
import com.softuni.exercice.booksystem.enums.EditionType;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getAllByCopiesLessThanAndEditionType(int copies, EditionType editionType);

    List<Book> getAllByPriceGreaterThanOrPriceLessThan(BigDecimal max, BigDecimal min);

    List<Book> getAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate releaseDate, LocalDate releaseDate2);

    List<Book> getAllByReleaseDateLessThan(LocalDate releaseDate);

    List<Book> getAllByTitleContaining(String title);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) >  :length")
    int countAllByTitleWithSymbolsMoreThan(int length);

    @Query("SELECT b.title , b.editionType,  b.ageRestriction ,  b.price FROM Book b WHERE b.title = :title")
    String getBookInfoByTitle(String title);

    List<Book> getAllByReleaseDateGreaterThan(LocalDate releaseDate);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.copies = b.copies + :increase WHERE b IN :books ")
    int increaseCopiesInGivenList(@Param("increase") int increase,
                                  @Param("books") List<Book> books);

    @Transactional
    public void removeAllByCopiesLessThan(int copies);

}
