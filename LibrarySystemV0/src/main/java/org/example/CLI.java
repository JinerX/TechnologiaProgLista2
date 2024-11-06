package org.example;

import java.util.Scanner;

//GRASP - Cohesion High cohesion the class serves one purpose
//GRASP - Coupling - minimal coupling reliant only on the LibraryRepository Interface
//GRASP Polymorphism - use of LibraryRepository Interface allows for easy replacing of data storage

/**
 * Command Line Interface (CLI) for interacting with the library.
 */
public class CLI {
  /**
   * The library repository for accessing library operations.
   */
  private final LibraryRepository libraryRepository;

  /**
   * Flag to control the running state of the CLI.
   */
  private boolean running;

  /**
   * Constructs a CLI with the specified library repository.
   *
   * @param libraryRepository The library repository could alternatively be a database
   */
  public CLI(final LibraryRepository libraryRepository) {
    if (libraryRepository == null) {
      throw new IllegalArgumentException("Library repository cannot be null");
    }
    this.libraryRepository = libraryRepository;
    this.running = false;
  }

  /**
   * Starts the CLI to accept user commands.
   */
  public void start() {
    this.running = true;
    final Scanner scanner = new Scanner(System.in);

    while (this.running) {
      System.out.print("Enter command: ");
      String command = null;

      try {
        command = scanner.nextLine();
      } catch (Exception e) {
        System.out.println("Error reading input: " + e.getMessage());
        System.exit(0);
      }

      if ("exit".equals(command)) {
        this.running = false;
        break;
      }

      final String[] commands = command.split(" ");
      final String op = commands[0];

      try {
        switch (op) {
          case "addUser":
            validateCommandLength(commands, 2);
            libraryRepository.addUser(commands[1]);
            System.out.println("User added successfully.");
            break;

          case "addBook":
            validateCommandLength(commands, 3);
            libraryRepository.addBook(commands[1], commands[2]);
            System.out.println("Book added successfully.");
            break;

          case "getBook":
            validateCommandLength(commands, 2);
            final int bookId = Integer.parseInt(commands[1]);
            System.out.println(libraryRepository.getBook(bookId));
            break;

          case "removeBook":
            validateCommandLength(commands, 2);
            final int removeBookId = Integer.parseInt(commands[1]);
            libraryRepository.removeBook(removeBookId);
            System.out.println("Book removed successfully.");
            break;

          case "removeUser":
            validateCommandLength(commands, 2);
            final int removeUserId = Integer.parseInt(commands[1]);
            libraryRepository.removeUser(removeUserId);
            System.out.println("User removed successfully.");
            break;

          case "getUser":
            validateCommandLength(commands, 2);
            final int userId = Integer.parseInt(commands[1]);
            System.out.println(libraryRepository.getUser(userId));
            break;

          case "returnBook":
            validateCommandLength(commands, 3);
            final int returnUserId = Integer.parseInt(commands[1]);
            final int returnBookId = Integer.parseInt(commands[2]);
            libraryRepository.returnBook(returnUserId, returnBookId);
            System.out.println("Book returned successfully.");
            break;

          case "loanBook":
            validateCommandLength(commands, 3);
            final int loanUserId = Integer.parseInt(commands[1]);
            final int loanBookId = Integer.parseInt(commands[2]);
            libraryRepository.loanBook(loanBookId, loanUserId);
            System.out.println("Book loaned successfully.");
            break;

          default:
            System.out.println("Invalid command. Please try again.");
        }
      } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
      }
    }

    scanner.close();
  }

  private void validateCommandLength(final String[] commands, final int expectedLength) {
    if (commands.length < expectedLength) {
      throw new IllegalArgumentException("Command has insufficient arguments.");
    }
  }
}
