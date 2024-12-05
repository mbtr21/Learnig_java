import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }
    public void addBook(Book book) {
        this.books.add(book);
    }

    public Book findBookByTitle(String title) {
        if (books == null || books.isEmpty()) {
            System.out.println("No Books Found");
            return null;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void findBookByAuthor(String author) {
        if (books == null || books.isEmpty()) {
            System.out.println("No Books Found");
            return;
        }
        ArrayList<Book> filteredBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                filteredBooks.add(book);
            }
        }
        for (Book book : filteredBooks) {
            System.out.println(book.getTitle() + " By " + book.getAuthor());
        }

    }

    public void deleteBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            books.remove(book);
            System.out.println("Book Deleted");
        } else {
            System.out.println("No Book Found");
        }
    }
    public void sortBooksByPublishDate() {
        if (books == null || books.isEmpty()) {
            System.out.println("No Books to Sort");
            return;
        }
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getPublishYear().compareTo(b2.getPublishYear());
            }
        });
        System.out.println("Books sorted by publish date.");
    }

    public void listAllBooks() {
        if (books == null || books.isEmpty()) {
            System.out.println("No Books Available");
            return;
        }
        for (Book book : books) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }

}
