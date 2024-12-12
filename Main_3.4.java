import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();


        Book book1 = new Book(
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                1925,
                null,
                null,
                Item.Status.Exist,
                Item.Type.Book
        );

        Magazine magazine1 = new Magazine(
                "National Geographic - July 2023",
                "Various Authors",
                null,
                null,
                Item.Status.Exist,
                Item.Type.Magazine,
                "Science"
        );

        Reference reference1 = new Reference(
                "Encyclopedia of Science",
                "Various Authors",
                "A comprehensive guide to scientific knowledge",
                null,
                null,
                Item.Status.Banned,
                Item.Type.Reference
        );


        library.add(book1);
        library.add(magazine1);
        library.add(reference1);


        for (Item item : library.itemList) {
            item.display();
        }

        Request borrowRequest = new Request("The Great Gatsby", Request.Type.Borrow);
        LinkedList<Request> requests = new LinkedList<>();
        requests.add(borrowRequest);
        ThreadManager threadManager = new ThreadManager(library);
        threadManager.writeToMemory(requests);
        threadManager.readFromMemory();
    }
}