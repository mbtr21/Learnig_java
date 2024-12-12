import java.util.ArrayList;
import java.util.Comparator;

public  class TitleSorter implements Library.ItemSorter {
    @Override
    public void sort(ArrayList<Item> items) {
        items.sort(Comparator.comparing(Item::getTitle));
    }
}

