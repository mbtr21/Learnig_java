import java.util.Date;

public class Reference extends Item{
    String description;
    private static final Type DEFAULT_TYPE = Type.Reference;
    private static final Status DEFAULT_STATUS = Status.Banned;
    private static final Date DEFAULT_BORROWED = null;
    private static final Date DEFAULT_RETURNED = null;

    public Reference(String title, String Author,String description ,Date borrowed, Date returned, Status status, Type type) {
        super(title, Author, (borrowed !=null)? borrowed : DEFAULT_BORROWED  , (returned != null) ? returned : DEFAULT_RETURNED, (status != null) ? status : DEFAULT_STATUS, (type != null) ? type : DEFAULT_TYPE);
        this.description = description;
    }


    @Override
    void display() {
        System.out.println("Title: " + getTitle() + "Author: " + getAuthor() + "DE");

    }
}
