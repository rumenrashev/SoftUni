package com.softuni.exercice.booksystem.repositories;

import com.softuni.exercice.booksystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> getAllByFirstNameEndingWith(String endsWith);

    List<Author> getAllByLastNameStartsWith(String lastName);

    Author findByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);

    @Query(value = "CALL getTotalAmountOfBooksByAuthorName(:full_name);", nativeQuery = true)
    public int getTotalAmountOfBooksByAuthorName(String full_name);

}
