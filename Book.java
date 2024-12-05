import java.util.Date;

public class Book {
    private String title;
    private String author;
    private Date publishYear;
    private Status status;
    public enum Status {
        Banned, Exist, Borrowed;
    }
    public Book(String title, String author, Date publishYear, Status status) {
        if (title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");}
        if (author == null || author.isEmpty()){
        throw new IllegalArgumentException("Author cannot be null or empty");}
        if (publishYear == null ){
            throw new IllegalArgumentException("Publish Year cannot be null");}
        if (status == null){
            throw new IllegalArgumentException("Status cannot be null");}
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.status = status;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Date getPublishYear() {
        return publishYear;
    }
    public Status getStatus() {
        return status;
    }
    public void setTitle(String title) {
        this.title = title.trim();
    }
    public void setAuthor(String author) {
        this.author = author.trim();
    }
    public void setPublishYear(Date publishYear) {
        this.publishYear = publishYear;

    }
    public void setStatus(Status status) {
        this.status = status;
    }

}
