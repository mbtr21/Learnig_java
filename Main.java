import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryMenu libraryMenu = new LibraryMenu();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
            LibraryMenu.displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": // Load books from file
                    libraryMenu.loadBooksFromFileInteractive();
                    break;
                case "2": // Display all books
                    LibraryMenu.displayAllBooks(); // Fixed: Use non-static method
                    break;
                case "3": // Search book by title
                    LibraryMenu.searchBookByTitle(); // Fixed: Use non-static method
                    break;
                case "4": // Get books by author
                    libraryMenu.getBooksByAuthor();
                    break;
                case "5": // Add a new book
                    LibraryMenu.addNewBook(); // Fixed: Use non-static method
                    break;
                case "6": // Delete a book
                    LibraryMenu.deleteBook(); // Fixed: Use non-static method
                    break;
                case "7": // Sort books by year
                    LibraryMenu.sortBooksByYear(); // Fixed: Use non-static method
                    break;
                case "8": // Exit
                    System.out.println("Exiting the library system. Goodbye!");
                    scanner.close(); // Close scanner at the end of the program
                    return;
                default: // Invalid input
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }
}
