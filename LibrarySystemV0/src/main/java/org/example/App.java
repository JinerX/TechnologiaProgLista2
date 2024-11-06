package org.example;

/**
 * Main application class to start the Library Management System.
 */
public class App {
  /**
   * main function of the program
   */
  public static void main(String[] args) {
    // GRASP - Creator, the CLI instance is created by the App class which directly uses the CLI
    final LibraryManagment lib = new LibraryManagment();
    final CLI cli = new CLI(lib);
    cli.start();
  }
}
