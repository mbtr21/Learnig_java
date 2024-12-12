import java.util.ArrayList;

public  class ItemManager {
    public int borrowItem(String title, ArrayList<Item> items) {
        TitleSearch titleSearch = new TitleSearch();
        int index = titleSearch.search(title, items);
        if (index != -1) {
            Item item = items.get(index);
            if (item.getStatus().equals(Item.Status.Banned) || item.getStatus().equals(Item.Status.Borrowed)) {
                return 0;
            }
            else {
                item.setStatus(Item.Status.Borrowed);
                return 1;
            }
        }
        return 0;
    }
    public int returnItem(String title, ArrayList<Item> items) {
        TitleSearch titleSearch = new TitleSearch();
        try {
            int index = titleSearch.search(title, items);
            items.get(index).setStatus(Item.Status.Exist);
            return 1;
        }
        catch (Exception e) {
            return 0;
        }
    }
}