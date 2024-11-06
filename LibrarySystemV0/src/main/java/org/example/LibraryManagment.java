package org.example;


import java.util.ArrayList;
import java.util.HashMap;

//GRASP - Cohesion: Minimal reliancy on other classes (for obvious reasons needs to be reliant on Book and User classes)

/**
 * Manages books and users within the library system. internal substitute for a database
 */
public class LibraryManagment implements LibraryRepository {
  /** Maps user identifiers to User objects for quick lookup. */
  private final HashMap<Integer, User> usersMap;

  /** Maps book identifiers to Book objects for quick lookup. */
  private final HashMap<Integer, Book> booksMap;

  /** List of users in the library system. */
  private final ArrayList<User> usersList;

  /** List of books in the library system. */
  private final ArrayList<Book> booksList;

  /** Counter to assign unique identifiers to new users. */
  private int newUserId;

  /** Counter to assign unique identifiers to new books. */
  private int newBookId;

  /**
   * Constructs a LibraryManagement system.
   */
  public LibraryManagment() {
    this.usersMap = new HashMap<>();
    this.booksMap = new HashMap<>();
    this.usersList = new ArrayList<>();
    this.booksList = new ArrayList<>();
    this.newUserId = 1;
    this.newBookId = 1;
  }

  @Override
  public void addUser(final String name) {

    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("User name cannot be null or empty");
    }

    final User user = new User(name, newUserId);
    newUserId = newUserId + 1;
    this.usersList.add(user);
    this.usersMap.put(user.getUserIdentifier(), user);
  }

  @Override
  public void addBook(final String author, final String title) {

    if (author == null || author.trim().isEmpty() || title == null || title.trim().isEmpty()) {
      throw new IllegalArgumentException("Author and title cannot be null or empty");
    }

    final Book book = new Book(title, author, newBookId);
    newBookId = newBookId + 1;
    this.booksList.add(book);
    this.booksMap.put(book.getBookIdentifier(), book);
  }

  @Override
  public Book getBook(final int id) {
    return this.booksMap.get(id);
  }

  @Override
  public void removeBook(final int id) {

    if (!this.booksMap.containsKey(id)) {
      throw new IllegalArgumentException("Book with ID " + id + " does not exist");
    }

    final Book book = this.booksMap.get(id);
    this.booksList.remove(book);
    this.booksMap.remove(id);
    adjustBookId();
  }

  @Override
  public void removeUser(final int id) {

    if (!this.usersMap.containsKey(id)) {
      throw new IllegalArgumentException("User with ID " + id + " does not exist");
    }

    final User user = this.usersMap.get(id);
    this.usersList.remove(user);
    this.usersMap.remove(id);
    adjustUserId();
  }

  @Override
  public User getUser(final int id) {
    return this.usersMap.get(id);
  }

  @Override
  public void returnBook(final int userId, final int bookId) {

    if (!this.usersMap.containsKey(userId) || !this.booksMap.containsKey(bookId)) {
      throw new IllegalArgumentException("User or Book ID does not exist");
    }

    final User user = this.usersMap.get(userId);
    final Book book = this.booksMap.get(bookId);
    user.removeBook(book);
  }

  @Override
  public void loanBook(final int bookId, final int userId) {

    if (!this.usersMap.containsKey(userId) || !this.booksMap.containsKey(bookId)) {
      throw new IllegalArgumentException("User or Book ID does not exist");
    }

    final User user = this.usersMap.get(userId);
    final Book book = this.booksMap.get(bookId);
    user.addBook(book);
  }

  /**
   *  utility class used when removing Users
   */
  public void adjustUserId() {
    usersMap.clear();

    for (int i = 0; i < usersList.size(); i++) {
      final User user = usersList.get(i);
      user.setUserIdentifier(i + 1);
      usersMap.put(user.getUserIdentifier(), user);
    }

    newUserId = usersList.size() + 1;
  }

  /**
   * utility class used when removing books
   */
  public void adjustBookId() {
    booksMap.clear();

    for (int i = 0; i < booksList.size(); i++) {
      final Book book = booksList.get(i);
      book.setBookIdentifier(i + 1);
      booksMap.put(book.getBookIdentifier(), book);
    }

    newBookId = booksList.size() + 1;
  }
}
