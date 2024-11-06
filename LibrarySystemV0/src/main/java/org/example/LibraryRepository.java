package org.example;

//GRASP - Polymorphism: an interface which is refered to by the CLI,
// allows ease of replacing the data storage system (for example on daatbase)
// GRASP - Choesion: High cohesion since it only offers the connection between CLI and data storage
// only one purpose
/**
 * Interface for managing library operations.
 */
public interface LibraryRepository {
  /**
   * adds user to the library system
   * @param name the name of the user to add.
   */
  void addUser(String name);

  /**
   * adds a new book to the library
   *
   * @param author author of the added book
   * @param title title of the added book
   */
  void addBook(String author, String title);

  /**
   * Retrieves a book from the library by its identifier.
   *
   * @param id identifier of the desired book
   * @return a book object corresponding to the id
   */
  Book getBook(int id);

  /**
   * Removes a book from the library by its identifier.
   *
   * @param id identifier of the removed book
   */
  void removeBook(int id);

  /**
   * Removes a user from the library by their identifier.
   *
   * @param id The unique identifier of the user to remove.
   */
  void removeUser(int id);

  /**
   * Retrieves a user from the library by their identifier.
   *
   * @param id The unique identifier of the user.
   * @return The user with the specified identifier, or null if not found.
   */
  User getUser(int id);

  /**
   * Returns a loaned book back to the library.
   *
   * @param bookId The identifier of the book to return.
   * @param userId The identifier of the user returning the book.
   */
  void returnBook(int bookId, int userId);

  /**
   * Loans a book to a user.
   *
   * @param bookId The identifier of the book to loan.
   * @param userId The identifier of the user borrowing the book.
   */
  void loanBook(int bookId, int userId);
}
