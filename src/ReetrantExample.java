import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReetrantExample {
    // as the lock is reentrant, so it can acquire the lock again, because same thread holds it.
    // this reentrant lock basically makes/ manages a count of lock and unlock and handle accordingly.
    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        lock.lock();
        try {
            System.out.println("Outer method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {

        //  this is where magic of reetrant locks comes into picture, else it will go in deadlock that is the same thread
        // waiting for the itself only to get complete and then proceed
        lock.lock();

        try {
            System.out.println("Inner Method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReetrantExample example = new ReetrantExample();
        example.outerMethod();
    }
}
