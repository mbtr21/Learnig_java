import java.util.Date;

public class Magazine extends Item {
    private String genre;
    private static final Type DEFAULT_TYPE = Type.Magazine;
    private static final Status DEFAULT_STATUS = Status.Exist;



    public Magazine(String title, String Author, Date borrowed, Date returned, Item.Status status, Type type, String genre) {
        super(title, Author, borrowed, returned, (status != null) ? status : DEFAULT_STATUS, (type != null) ? type : DEFAULT_TYPE);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    void display() {
        System.out.println("Title: " + getTitle() + "Author: " + getAuthor() + "Genre: " + getGenre());
    }
}
