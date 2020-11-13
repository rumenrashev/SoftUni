package com.softuni.exercice.booksystem.constants;

import static com.softuni.exercice.booksystem.constants.GlobalConstants.DELIMITER;

public class Conditions {

    public static final String TASK_01_CONDITION =
            DELIMITER +
                    "  \n  1. Books Titles by Age Restriction\n" +
                    "Write a program that prints the titles of all books, \n" +
                    "for which the age restriction matches the given input (minor, teen or adult). \n" +
                    "Ignore casing of the input.\n" +
                    DELIMITER + " \n";

    public static final String TASK_02_CONDITION =
            DELIMITER +
                    "\n   2. Golden Books\n" +
                    "Write a program that prints the titles of the golden edition books, \n" +
                    "which have less than 5000 copies.\n" +
                    DELIMITER;

    public static final String TASK_03_CONDITION =
            DELIMITER +
                    "\n    3. Books by Price\n" +
                    "Write a program that prints the titles and prices of books with price lower than 5 and higher than 40.\n"
                    + DELIMITER;

    public static final String TASK_04_CONDITION =
            DELIMITER +
                    "\n    4. Not Released Books\n" +
                    "Write a program that prints the titles of all books that are NOT released in a given year.\n"
                    + DELIMITER;

    public static final String TASK_05_CONDITION =
            DELIMITER +
                    " \n   5. Books Released Before Date\n" +
                    "Write a program that prints the title, the edition type and the price of books, \n" +
                    "which are released before a given date. The date will be in the format dd-MM-yyyy.\n" +
                    DELIMITER;

    public static final String Task_06_CONDITION =
            DELIMITER +
                    "\n    6. Authors Search\n" +
                    "Write a program that prints the names of those authors, whose first name ends with a given string.\n"
                    + DELIMITER;

    public static final String TASK_07_CONDITION =
            DELIMITER +
                    "\n    7. Books Search\n" +
                    "Write a program that prints the titles of books, which contain a given string (regardless of the casing).\n" +
                    DELIMITER;

    public static final String TASK_08_CONDITION =
            DELIMITER +
                    "\n    8. Book Titles Search\n" +
                    "Write a program that prints the titles of books, which are written by authors, whose last name starts with a given string.\n" +
                    DELIMITER;

    public static final String TASK_09_CONDITION =
            DELIMITER +
                    "\n    9. Count Books\n" +
                    "Write a program that prints the number of books, whose title is longer than a given number.\n" +
                    DELIMITER;

    public static final String TASK_10_CONDITION =
            DELIMITER +
                    "\n    10. Total Book Copies\n" +
                    "Write a program that prints the total number of book copies by author. Order the results descending by total book copies.\n" +
                    DELIMITER;

    public static final String TASK_11_CONDITION =
            DELIMITER +
                    " \n   11. Reduced Book\n" +
                    "Write a program that prints information (title, edition type, age restriction and price) for a book by given title. \n" +
                    "When retrieving the book information select only those fields and do NOT include any other information in the returned result.\n"
                    + DELIMITER;

    public static final String TASK_12_CONDITION =
            DELIMITER +
                    "\n    12. * Increase Book Copies\n" +
                    "Write a program that increases the copies of all books released after a given date with a given number. \n" +
                    "Print the total amount of book copies that were added.\n" +
                    "Input\n" +
                    "    • On the first line – date in the format dd MMM yyyy. If a book is released after that date (exclusively),\n" +
                    " increase its book copies with the provided number from the second line of the input.\n" +
                    "    • On the second line – number of book copies each book should be increased with.\n" +
                    "Output\n" +
                    "    • Total number of books that was added to the database.\n" +
                    DELIMITER;

    public static final String TASK_13_CONDITION =
            DELIMITER +
                    "\n    13. * Remove Books\n" +
                    "Write a program that removes from the database those books, which copies are lower than a given number. \n" +
                    "Print the number of books that were deleted from the database.\n" +
                    DELIMITER;

    public static final String TASK_14_CONDITION =
            DELIMITER +
                    "\n    14. * Stored Procedure\n" +
                    "Using Workbench (or other similar tool) create a stored procedure, which receives an author's first and last name \n" +
                    "and returns the total amount of books the author has written. Then write a program that receives an author's name \n" +
                    "and prints the total number of books the author has written by using the stored procedure you've just created.\n" +
                    DELIMITER;
}
