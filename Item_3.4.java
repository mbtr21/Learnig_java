import java.util.Date;

abstract class Item {
    String title;
    String Author;
    Date borrowed;
    Date returned;
    Status status;
    Type type;
    RequestType requestType;


    public Item(String title, String author, Date borrowed, Date returned, Type type) {
    }

    enum Status{
        Exist,Borrowed,Banned
    }


    enum Type{
        Book, Magazine, Reference
    }


    enum RequestType{
        Return, Borrow, Nothing
    }


    public  Item(String title, String Author, Date borrowed, Date returned, Status status, Type type){
        this.title = title;
        this.Author = Author;
        this.borrowed = borrowed;
        this.returned = returned;
        this.status = status;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }


    public RequestType getRequestType() {
        return requestType;
    }


    public String getAuthor() {
        return Author;
    }


    public Date getBorrowed() {
        return borrowed;
    }


    public Date getReturned() {
        return returned;
    }


    public Status getStatus() {
        return status;
    }


    public Type getType() {
        return type;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setAuthor(String Author) {
        this.Author = Author;

    }


    public void setBorrowed(Date borrowed) {
        this.borrowed = borrowed;
    }


    public void setReturned(Date returned) {
        this.returned = returned;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public void setType(Type type) {
        this.type = type;
    }


    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }


    abstract void  display();

}
