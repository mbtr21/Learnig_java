import java.util.ArrayList;
import java.util.Comparator;

public class ByStatus implements Library.ItemSorter {
    @Override
    public void sort(ArrayList<Item> items) {
        items.sort(Comparator.comparing(Item::getStatus));
    }
}