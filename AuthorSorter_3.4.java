import java.util.ArrayList;
import java.util.Comparator;

public  class AuthorSorter implements Library.ItemSorter {
    @Override
    public void sort(ArrayList<Item> items) {
        items.sort(Comparator.comparing(Item::getAuthor));
    }
}