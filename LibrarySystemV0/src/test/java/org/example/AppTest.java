package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    // Test the basic creation of a book
    public void testBookCreation() {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1);
        assertTrue(book.toString().contains("The Great Gatsby"));
        assertTrue(book.toString().contains("F. Scott Fitzgerald"));
        assertFalse(book.getLoaned());
    }

    // Test loaning a book successfully
    public void testLoanBook() {
        Book book = new Book("1984", "George Orwell", 2);
        book.loanBook();
        assertTrue(book.getLoaned());
    }

    // Test returning a book successfully
    public void testReturnBook() {
        Book book = new Book("1984", "George Orwell", 2);
        book.loanBook(); // Book is now loaned
        book.returnBook();
        assertFalse(book.getLoaned());
    }

    // Test exception when trying to return a book not loaned
    public void testReturnBookNotLoaned() {
        Book book = new Book("1984", "George Orwell", 2);
        try {
            book.returnBook();
            fail("Expected IllegalStateException not thrown");
        } catch (IllegalStateException e) {
            // Expected exception
        }
    }

    // Test adding a user
    public void testAddUser() {
        LibraryManagment lib = new LibraryManagment();
        lib.addUser("John Doe");
        assertNotNull(lib.getUser(1));
    }

    // Test adding a book
    public void testAddBook() {
        LibraryManagment lib = new LibraryManagment();
        lib.addBook("J.K. Rowling", "Harry Potter");
        assertNotNull(lib.getBook(1));
    }

    // Test loaning a book to a user
    public void testLoanBookToUser() {
        LibraryManagment lib = new LibraryManagment();
        lib.addUser("Alice");
        lib.addBook("J.R.R. Tolkien", "The Hobbit");
        Book book = lib.getBook(1);
        User user = lib.getUser(1);
        lib.loanBook(1, 1);
        assertTrue(user.getBooksLoaned().contains(book));
    }

    // Test returning a book by user
    public void testReturnBookByUser() {
        LibraryManagment lib = new LibraryManagment();
        lib.addUser("Bob");
        lib.addBook("J.K. Rowling", "Harry Potter");
        Book book = lib.getBook(1);
        User user = lib.getUser(1);
        lib.loanBook(1, 1);
        lib.returnBook(1, 1);
        assertFalse(user.toString().contains("Harry Potter"));
    }

    // Test invalid user removal (user does not exist)
    public void testRemoveNonExistingUser() {
        LibraryManagment lib = new LibraryManagment();
        try {
            lib.removeUser(1);
            fail("Expected IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    // Test removing a book
    public void testRemoveBook() {
        LibraryManagment lib = new LibraryManagment();
        lib.addBook("George Orwell", "Animal Farm");
        lib.removeBook(1);
        assertNull(lib.getBook(1));
    }

    // Test invalid book removal (book does not exist)
    public void testRemoveNonExistingBook() {
        LibraryManagment lib = new LibraryManagment();
        try {
            lib.removeBook(999);
            fail("Expected IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        TestSuite suite = new TestSuite();

        // Add the tests to the suite
        suite.addTest(new AppTest("testBookCreation"));
        suite.addTest(new AppTest("testLoanBook"));
        suite.addTest(new AppTest("testReturnBook"));
        suite.addTest(new AppTest("testReturnBookNotLoaned"));
        suite.addTest(new AppTest("testAddUser"));
        suite.addTest(new AppTest("testAddBook"));
        suite.addTest(new AppTest("testLoanBookToUser"));
        suite.addTest(new AppTest("testReturnBookByUser"));
        suite.addTest(new AppTest("testRemoveNonExistingUser"));
        suite.addTest(new AppTest("testRemoveBook"));
        suite.addTest(new AppTest("testRemoveNonExistingBook"));

        return suite;
    }
}

