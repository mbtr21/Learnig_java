import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public  class FileDataLoader implements Library.DataLoader {
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