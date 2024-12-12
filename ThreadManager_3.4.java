import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadManager {
    private final Queue<Request> requestQueue;
    private final Lock lock;
    private final ItemThreadManager itemThreadManager;
    private final UserThread userThread;


    public ThreadManager(Library library) {
        requestQueue = new LinkedList<>();
        lock = new ReentrantLock();
        itemThreadManager = new ItemThreadManager(library);
        userThread = new UserThread(requestQueue);
    }


    public void writeToMemory(LinkedList<Request> requests) {
        userThread.setUserRequests(requests);
        lock.lock();
        try {
            userThread.run();
        } finally {
            lock.unlock();
        }
    }


    public void readFromMemory() {
        lock.lock();
        try {
            itemThreadManager.run();
        } finally {
            lock.unlock();
        }
    }
}


