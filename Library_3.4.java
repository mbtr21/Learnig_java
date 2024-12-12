import java.util.ArrayList;


public class Library {

    public final ArrayList<Item> itemList;


    public Library() {
        this.itemList = new ArrayList<>();
    }


    public interface ItemSorter {
        void sort(ArrayList<Item> items);
    }



    public void sort(ArrayList<Item> items, ItemSorter sorter) {
        sorter.sort(items);
    }


    public interface Researcher {
        int search(String keyword, ArrayList<Item> items);
    }


    public interface DataLoader {
        void loadData(ArrayList<Item> items);
    }


    public void add(Item item) {
        itemList.add(item);
    }


    public void remove(String keyword ,Researcher researcher ) {
        itemList.remove(search(keyword, researcher));
    }


    public int search(String keyword, Researcher researcher) {
        return researcher.search(keyword, itemList);
    }


    public int borrowItem(String title) {
        ItemManager itemManager = new ItemManager();
        int response = itemManager.borrowItem(title, itemList);
        return response;
    }


    public int returnItem(String title) {
        ItemManager itemManager = new ItemManager();
        int response = itemManager.returnItem(title, itemList);
        return response;
    }



}
