import java.util.Date;


public class Book extends Item {
    Integer publishedYear;
    String description;
    private static final Type DEFAULT_TYPE = Type.Book;
    private static final Status DEFAULT_STATUS = Status.Exist;

    public Book(String title, String Author,Integer publishedYear ,Date borrowed, Date returned, Status status, Type type) {
        super(title, Author, borrowed, returned, (status != null) ? status : DEFAULT_STATUS, (type != null) ? type : DEFAULT_TYPE);
        this.publishedYear = publishedYear;
        this.description = title;
    }

    Integer getPublishedYear() {
        return publishedYear;
    }


    String getDescription() {
        return description;
    }


    void setDescription(String description) {
        this.description = description;
    }


    void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }


    @Override
    void display() {
        System.out.println("Title: " + getTitle() + "Author: " + getAuthor() + "Published Year: " + getPublishedYear() + "Description: " + getDescription());
    }
}
