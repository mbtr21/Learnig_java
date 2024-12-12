public class Request {
    private String title;
    private Type type ;
    enum Type {
        Borrow, Return, None
    }


    public Request(String title, Type type) {
        this.title = title;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }


    public Type getType() {
        return type;

    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setType(Type type) {
        this.type = type;
    }


}
