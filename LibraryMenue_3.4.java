import java.util.Scanner;

class LibraryMenu {
    private static final Library library = new Library();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Sample items added to library
        library.add(new Book("Java Programming", "John Doe", 2020, null, null, Item.Status.Exist, Item.Type.Book));
        library.add(new Magazine("Tech Today", "Jane Smith", null, null, Item.Status.Exist, Item.Type.Magazine, "Technology"));

        while (true) {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    addItem(scanner);
                    break;
                case 2:
                    removeItem(scanner);
                    break;
                case 3:
                    searchItem(scanner);
                    break;
                case 4:
                    sortItems(scanner);
                    break;
                case 5:
                    borrowItem(scanner);
                    break;
                case 6:
                    returnItem(scanner);
                    break;
                case 7:
                    loadDataFromFile(scanner);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Display the main menu
    private static void displayMenu() {
        System.out.println("\nLibrary Menu:");
        System.out.println("1. Add item");
        System.out.println("2. Remove item");
        System.out.println("3. Search item");
        System.out.println("4. Sort items");
        System.out.println("5. Borrow item");
        System.out.println("6. Return item");
        System.out.println("7. Load data from file");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    // Add a new item to the library
    private static void addItem(Scanner scanner) {
        System.out.print("Enter the type of item (Book, Magazine, Reference): ");
        String type = scanner.nextLine();
        System.out.print("Enter the title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author: ");
        String author = scanner.nextLine();

        Item item = null;
        if (type.equalsIgnoreCase("Book")) {
            System.out.print("Enter the published year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            item = new Book(title, author, year, null, null, Item.Status.Exist, Item.Type.Book);
        } else if (type.equalsIgnoreCase("Magazine")) {
            System.out.print("Enter the genre: ");
            String genre = scanner.nextLine();
            item = new Magazine(title, author, null, null, Item.Status.Exist, Item.Type.Magazine, genre);
        } else if (type.equalsIgnoreCase("Reference")) {
            System.out.print("Enter the description: ");
            String description = scanner.nextLine();
            item = new Reference(title, author, description, null, null, Item.Status.Exist, Item.Type.Reference);
        }

        if (item != null) {
            library.add(item);
            System.out.println("Item added successfully.");
        } else {
            System.out.println("Invalid item type.");
        }
    }

    // Remove an item from the library
    private static void removeItem(Scanner scanner) {
        System.out.print("Enter the title of the item to remove: ");
        String title = scanner.nextLine();
        TitleSearch researcher = new TitleSearch();
        library.search(title, researcher ); // Check if item exists
        Item item = null;

        for (Item i : library.itemList) {
            if (i.getTitle().equals(title)) {
                item = i;
                break;
            }
        }

        if (item != null) {
            library.remove(title, researcher);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found.");
        }
    }

    // Search for an item by title or author
    private static void searchItem(Scanner scanner) {
        System.out.print("Enter the search method (author/title): ");
        String method = scanner.nextLine();
        System.out.print("Enter the search keyword: ");
        String keyword = scanner.nextLine();

        Library.Researcher researcher = null;
        if (method.equalsIgnoreCase("author")) {
            researcher = new AuthorSearch();
        } else if (method.equalsIgnoreCase("title")) {
            researcher = new TitleSearch();
        }

        if (researcher != null) {
            int resultIndex = researcher.search(keyword, library.itemList);
            if (resultIndex != -1) {
                Item item = library.itemList.get(resultIndex);
                System.out.println("Found item: " + item.getTitle() + " by " + item.getAuthor());
            } else {
                System.out.println("Item not found.");
            }
        } else {
            System.out.println("Invalid search method.");
        }
    }

    // Sort items based on a selected criterion
    private static void sortItems(Scanner scanner) {
        System.out.print("Enter the sort method (author/title/status): ");
        String method = scanner.nextLine();

        Library.ItemSorter sorter = null;
        if (method.equalsIgnoreCase("author")) {
            sorter = new AuthorSorter();
        } else if (method.equalsIgnoreCase("title")) {
            sorter = new TitleSorter();
        } else if (method.equalsIgnoreCase("status")) {
            sorter = new ByStatus();
        }

        if (sorter != null) {
            library.sort(library.itemList, sorter);
            System.out.println("Items sorted by " + method + ".");
        } else {
            System.out.println("Invalid sort method.");
        }
    }

    // Borrow an item
    private static void borrowItem(Scanner scanner) {
        System.out.print("Enter the title of the item to borrow: ");
        String title = scanner.nextLine();
        library.borrowItem(title); // Check if item exists and not borrowed or banned
    }

    // Return an item
    private static void returnItem(Scanner scanner) {
        System.out.print("Enter the title of the item to return: ");
        String title = scanner.nextLine();
        library.returnItem(title);
        System.out.println("Item returned successfully.");
    }

    // Load data from a file
    private static void loadDataFromFile(Scanner scanner) {
        Library.DataLoader dataLoader = new FileDataLoader();
        dataLoader.loadData(library.itemList);
        System.out.println("Data loaded successfully.");
    }
}
