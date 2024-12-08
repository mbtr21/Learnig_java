public class Library {
    static class LinkListNode<T> {
        T data;
        LinkListNode<T> next;
        LinkListNode<T> prev; // Add prev for doubly linked list

        LinkListNode(T data) {
            this.data = data;
            this.next = null;
            this.prev = null; // Initialize prev to null
        }
    }

    private LinkListNode<Book> head;
    private LinkListNode<Book> tail; // Add a tail pointer for efficiency

    // Add a new book at the end of the listb
    public void addBook(Book book) {
        LinkListNode<Book> newBook = new LinkListNode<>(book);
        if (head == null) {
            head = newBook;
            tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail; // Link the previous node
            tail = newBook; // Update tail
        }
    }

    // Sort books by publish year using bubble sort (adapted for doubly linked list)
    public void sortBooksByYear() {
        if (head == null || head.next == null) return;

        boolean swapped;
        do {
            swapped = false;
            LinkListNode<Book> current = head;

            while (current != null && current.next != null) {
                if (current.data.getPublishYear() > current.next.data.getPublishYear()) {
                    swapped = true;

                    // Swap nodes
                    Book temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                }
                current = current.next;
            }
        } while (swapped);
    }

    // Delete a book by title
    public boolean deleteBook(String title) {
        if (head == null) return false;

        LinkListNode<Book> temp = head;

        // Deleting the head node
        if (head.data.getTitle().equalsIgnoreCase(title)) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null; // Update tail if the list is empty
            return true;
        }

        // Traverse to find the node to delete
        while (temp != null && !temp.data.getTitle().equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        // Node not found
        if (temp == null) return false;

        // Adjust links to remove the node
        if (temp.next != null) temp.next.prev = temp.prev;
        if (temp.prev != null) temp.prev.next = temp.next;

        // If the node is the tail, update tail
        if (temp == tail) tail = temp.prev;

        return true;
    }

    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("The book list is empty");
            return;
        }
        LinkListNode<Book> temp = head;
        while (temp != null) {
            System.out.println(temp.data.getTitle());
            temp = temp.next;
        }
    }

    // Find a book by title
    public Book findBookWithTitle(String title) {
        LinkListNode<Book> temp = head;
        while (temp != null) {
            if (temp.data.getTitle().equalsIgnoreCase(title)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    // Find books by author
    public LinkListNode<Book> findBooksWithAuthor(String author) {
        LinkListNode<Book> temp = head;
        LinkListNode<Book> resultHead = null;
        LinkListNode<Book> resultTail = null;

        while (temp != null) {
            if (temp.data.getAuthor().equalsIgnoreCase(author)) {
                LinkListNode<Book> newNode = new LinkListNode<>(temp.data);
                if (resultHead == null) {
                    resultHead = newNode;
                    resultTail = newNode;
                } else {
                    resultTail.next = newNode;
                    newNode.prev = resultTail;
                    resultTail = newNode;
                }
            }
            temp = temp.next;
        }
        return resultHead;
    }

    // Display books by status (unchanged)
    public void displayBooksByStatus() {
        LinkListNode<Book> temp = head;
        while (temp != null) {
            System.out.println(temp.data.getTitle());
            temp = temp.next;
        }
    }
}
