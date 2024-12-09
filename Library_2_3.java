import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class Library {

    private final ArrayList<Item> itemList;

    public Library() {
        this.itemList = new ArrayList<>();
    }


    public interface ItemSorter {
        void sort(ArrayList<Item> items);
    }


    public static class AuthorSorter implements ItemSorter {
        @Override
        public void sort(ArrayList<Item> items) {
            items.sort(Comparator.comparing(Item::getAuthor));
        }
    }


    public static class TitleSorter implements ItemSorter {
        @Override
        public void sort(ArrayList<Item> items) {
            items.sort(Comparator.comparing(Item::getTitle));
        }
    }


    public static class ByStatus implements ItemSorter {
        @Override
        public void sort(ArrayList<Item> items) {
            items.sort(Comparator.comparing(Item::getStatus));
        }
    }


    public void sort(ArrayList<Item> items, ItemSorter sorter) {
        sorter.sort(items);
    }


    public interface SearchMethod {
        int search(String keyword, ArrayList<Item> items);
    }


    public static class AuthorSearch implements SearchMethod {
        @Override
        public int search(String keyword, ArrayList<Item> items) {
            int low = 0;
            int high = items.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                String midAuthor = items.get(mid).getAuthor();
                if (midAuthor.equals(keyword)) {
                    return mid;
                } else if (midAuthor.compareTo(keyword) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }
    }


    public static class TitleSearch implements SearchMethod {
        @Override
        public int search(String keyword, ArrayList<Item> items) {
            int low = 0;
            int high = items.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                String midTitle = items.get(mid).getTitle();
                if (midTitle.equals(keyword)) {
                    return mid;
                } else if (midTitle.compareTo(keyword) < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return -1;
        }
    }

    public static class ItemManager {
        public void getItem(String title, ArrayList<Item> items) {
            TitleSearch titleSearch = new TitleSearch();
            int index = titleSearch.search(title, items);
            if (index != -1) {
                Item item = items.get(index);
                if (item.getStatus().equals(Item.Status.Banned) || item.getStatus().equals(Item.Status.Borrowed)) {
                    return;
                }
            }
            else {
                System.out.println("No Book Founded");
            }
        }
        public void returnItem(String title, ArrayList<Item> items) {
            TitleSearch titleSearch = new TitleSearch();
            int index = titleSearch.search(title, items);
            items.get(index).setStatus(Item.Status.Exist);
        }
    }

    public interface DataLoader {
        void loadData(ArrayList<Item> items);
    }
    public static class FileDataLoader implements DataLoader {
        @Override
        public void loadData(ArrayList<Item> items) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter file path: ");
            String filePath = scanner.nextLine();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split(",");
                    String type = parts[0].trim();
                    String title = parts[1].trim();
                    String author = parts[2].trim();
                    Date borrowed = parseDate(parts[3].trim());
                    Date returned = parseDate(parts[4].trim());
                    Item.Status status = Item.Status.valueOf(parts[5].trim().toUpperCase());

                    // Create corresponding item based on type
                    Item item = null;
                    if (type.equalsIgnoreCase("Book")) {
                        Integer publishedYear = Integer.valueOf(parts[6].trim());
                        String description = parts[7].trim();
                        item = new Book(title, author, publishedYear, borrowed, returned, status, Item.Type.Book);
                    } else if (type.equalsIgnoreCase("Magazine")) {
                        String genre = parts[6].trim();
                        item = new Magazine(title, author, borrowed, returned, status, Item.Type.Magazine, genre);
                    } else if (type.equalsIgnoreCase("Reference")) {
                        String description = parts[6].trim();
                        item = new Reference(title, author, description, borrowed, returned, status, Item.Type.Reference);
                    }
                    if (item != null) {
                        items.add(item);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error parsing the data: " + e.getMessage());
            }
        }
        private Date parseDate(String dateStr) {
            try {
                if (dateStr.isEmpty()) {
                    return null;
                }
                return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr); // Date format "yyyy-MM-dd"
            } catch (Exception e) {
                return null;
            }
        }


    }

    public void add(Item item) {
        itemList.add(item);
    }

    public void remove(Item item) {
        itemList.remove(item);
    }
    public void getItem(String title) {
        ItemManager itemManager = new ItemManager();
        itemManager.getItem(title, itemList);
    }
    public void returnItem(String title) {
        ItemManager itemManager = new ItemManager();
        itemManager.returnItem(title, itemList);
    }
}
