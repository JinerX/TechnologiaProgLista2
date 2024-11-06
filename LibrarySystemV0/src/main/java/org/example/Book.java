package org.example;

//GRASP - Expert Book class manages it's own status (author, identifier, title, loaned status)
//GRASP - Cohesion High cohesion, the Book class represents, provides relevant methods
// without implementing functionality

/**
 * Represents a Book with a title, author, identifier, and loan status.
 */
public class Book {
  /** The title of the book. */
  private final String title;

  /** The author of the book. */
  private final String author;

  /** Unique identifier for the book. */
  private int bookIdentifier;

  /** Loan status of the book: true if loaned, false otherwise. */
  private Boolean loaned;

  /**
   * Constructs a Book with specified title, author, and identifier.
   *
   * @param title The title of the book.
   * @param author The author of the book.
   * @param bookIdentifier Unique identifier for the book.
   */
  public Book(final String title, final String author, final int bookIdentifier) {

    if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
      throw new IllegalArgumentException("Title and author cannot be null or empty");
    }

    this.title = title;
    this.author = author;
    this.bookIdentifier = bookIdentifier;
    this.loaned = false;
  }

  /**
   * Marks the book as loaned.
   *
   * @throws IllegalStateException if the book is already loaned.
   */
  public void loanBook() {
    if (this.loaned) {
      throw new IllegalStateException("Book is already loaned");
    }
    this.loaned = true;
  }

  /**
   * Marks the book as returned.
   *
   * @throws IllegalStateException if the book is not loaned.
   */
  public void returnBook() {
    if (!this.loaned) {
      throw new IllegalStateException("Book is not loaned");
    }
    this.loaned = false;
  }

  /**
   * loaned parameter getter
   *
   * @return True if loaned, false otherwise.
   */
  public Boolean getLoaned() {
    return loaned;
  }

  public int getBookIdentifier() {
    return bookIdentifier;
  }

  /**
   *
   * @param bookIdentifier the id of the desired book
   */
  public void setBookIdentifier(final int bookIdentifier) {

    if (bookIdentifier <= 0) {
      throw new IllegalArgumentException("Book identifier must be positive");
    }

    this.bookIdentifier = bookIdentifier;
  }

  @Override
  public String toString() {
    return "Book {title=" + title  + "\n"
        + "author=" + author + "\n"
        + "bookIdentifier=" + bookIdentifier
        + "loaned=" + loaned + "}";
  }
}
