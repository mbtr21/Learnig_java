import java.util.LinkedList;
import java.util.Queue;

class UserThread implements Runnable {
    private final Queue<Request> requests; // Shared memory
    private LinkedList<Request> userRequests; // User-provided requests

    public UserThread(Queue<Request> requests) {
        this.requests = requests;
        this.userRequests = new LinkedList<>(); // Initialize to avoid null issues
    }

    public void setUserRequests(LinkedList<Request> userRequests) {
        if (userRequests != null) {
            this.userRequests = userRequests;
        } else {
            this.userRequests = new LinkedList<>(); // Default to an empty list if null
        }
    }

    public int writeToRequests() {
        try {
            synchronized (requests) { // Ensure thread safety when accessing the shared queue
                for (Request request : userRequests) {
                    requests.add(request);
                }
            }
            userRequests.clear(); // Clear the local list after adding to the shared queue
            return 1; // Indicate success
        } catch (Exception e) {
            System.err.println("Error writing to requests: " + e.getMessage());
            return 0; // Indicate failure
        }
    }

    @Override
    public void run() {
        int result = writeToRequests();
        OutputHandel handler = new OutputHandel();
        handler.setResponse(result);
        handler.printMessage();

    }
}
