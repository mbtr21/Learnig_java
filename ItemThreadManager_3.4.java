import java.util.LinkedList;
import java.util.Queue;

class ItemThreadManager implements Runnable {
    private final Library library;
    private Queue<Request> requests;

    public ItemThreadManager(Library library) {
        this.library = library;
        this.requests = new LinkedList<>(); // Initialize the queue to avoid NullPointerException
    }

    public void setRequests(Queue<Request> requests) {
        this.requests = requests;
    }

    @Override
    public void run() {
        if (requests == null || requests.isEmpty()) {
            OutputHandel handler = new OutputHandel();
            handler.setResponse(0);
            handler.printMessage();
            return;
        }

        int response = -1;
        OutputHandel outputHandel = new OutputHandel();
        for (Request request : requests) {
            if (request.getType() == Request.Type.Borrow) {
                response = library.borrowItem(request.getTitle());
            } else if (request.getType() == Request.Type.Return) {
                response = library.returnItem(request.getTitle());
                request.setType(Request.Type.None);
            }
            outputHandel.setResponse(response);
            outputHandel.printMessage();
        }
    }
}