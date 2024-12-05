import java.util.ArrayList;
import java.util.Comparator;

public class Library {
    private BookNode<Book> books;

    // BookNode class to represent a linked list node holding a book
    public static class BookNode<T> {
        T data;
        BookNode<T> next = null;

        public BookNode(T data) {
            this.data = data;
        }

        // Setter for next node in the linked list
        public void setNext(BookNode<T> next) {
            this.next = next;
        }
    }

    // Constructor to initialize the linked list with a single book
    public Library(Book book) {
        this.books = new BookNode<>(book); // Initialize with the first book
    }

    // Method to add a book to the linked list
    public void addBook(Book book) {
        BookNode<Book> newBookNode = new BookNode<>(book);
        if (books == null) {
            books = newBookNode; // If the list is empty, set the new node as the head
        } else {
            BookNode<Book> current = books;
            // Traverse to the last node
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(newBookNode); // Add the new node at the end
        }
    }

    // Method to find a book by title
    public Book findBookByTitle(String title) {
        BookNode<Book> current = books;
        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                return current.data;
            }
            current = current.next;
        }
        System.out.println("No Book Found");
        return null;
    }

    // Method to find books by author
    public void findBookByAuthor(String author) {
        BookNode<Book> current = books;
        boolean found = false;
        while (current != null) {
            if (current.data.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(current.data.getTitle() + " by " + current.data.getAuthor());
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No books found by this author.");
        }
    }

    // Method to delete a book by title
    public void deleteBook(String title) {
        BookNode<Book> current = books;
        BookNode<Book> previous = null;
        while (current != null) {
            if (current.data.getTitle().equalsIgnoreCase(title)) {
                if (previous == null) {
                    books = current.next; // If the book is the head, set the next node as the new head
                } else {
                    previous.setNext(current.next); // Bypass the current node
                }
                System.out.println("Book Deleted");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("No Book Found");
    }

    // Method to sort books by publish year
    public void sortBooksByPublishDate() {
        if (books == null) {
            System.out.println("No Books to Sort");
            return;
        }

        // Convert linked list to array for sorting
        ArrayList<Book> bookList = new ArrayList<>();
        BookNode<Book> current = books;
        while (current != null) {
            bookList.add(current.data);
            current = current.next;
        }

        // Sort the list
        bookList.sort(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getPublishYear().compareTo(b2.getPublishYear());
            }
        });

        // Rebuild the linked list with sorted books
        books = null;
        for (Book book : bookList) {
            addBook(book);
        }
        System.out.println("Books sorted by publish date.");
    }

    // Method to list all books
    public void listAllBooks() {
        if (books == null) {
            System.out.println("No Books Available");
            return;
        }
        BookNode<Book> current = books;
        while (current != null) {
            System.out.println(current.data.getTitle() + " by " + current.data.getAuthor() + " - Status: " + current.data.getStatus());
            current = current.next;
        }
    }
}
