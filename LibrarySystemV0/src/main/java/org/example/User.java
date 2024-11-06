package org.example;

import java.util.ArrayList;

//GRASP - Expert the User class itself manages the information about User (name, identifier, list of loaned books)
//GRASP - Cohesion High cohesion, the Book class represents, provides relevant methods, without implementing functionality

/**
 * Represents a library user with a unique identifier and loaned books.
 */
public class User {
  /** The name of the user. */
  private final String name;

  /** Unique identifier for the user. */
  private int userIdentifier;

  /** List of books currently loaned by the user. */
  private ArrayList<Book> booksLoaned;

  /**
   * Constructs a User with specified name and identifier.
   *
   * @param name The name of the user.
   * @param userIdentifier Unique identifier for the user.
   */
  public User(final String name, final int userIdentifier) {

    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("User name cannot be null or empty");
    }

    this.name = name;
    this.userIdentifier = userIdentifier;
    this.booksLoaned = new ArrayList<>();
  }

  /**
   * Adds a book to the user's loaned books.
   *
   * @param book The book to loan.
   */

  public void addBook(final Book book) {

    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }

    book.loanBook();
    booksLoaned.add(book);
  }

  /**
   * Removes a book from the user's loaned books.
   *
   * @param book The book to return.
   */
  public void removeBook(final Book book) {

    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }
    if (!booksLoaned.contains(book)) {
      throw new IllegalStateException("User does not have this book loaned");
    }

    book.returnBook();
    booksLoaned.remove(book);
  }

  public int getUserIdentifier() {
    return userIdentifier;
  }

  /** support function used to adjust the identifiers when removing users
   *
   * @param userIdentifier the value to which the identifier will be set
   */
  public void setUserIdentifier(final int userIdentifier) {

    if (userIdentifier <= 0) {
      throw new IllegalArgumentException("User identifier must be positive");
    }

    this.userIdentifier = userIdentifier;
  }

  /**
   * get a list of books loaned by the user
   *
   * @return the array list of loaned books
   */
  public ArrayList<Book> getBooksLoaned() {
    return booksLoaned;
  }

  @Override
  public String toString() {
    return "User {name="
        + name + "\n"
        + "userIdentifier="
        + userIdentifier + "}";
  }
}
