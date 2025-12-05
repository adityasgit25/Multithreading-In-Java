import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {

    /*
    * when there is starvation condition, like when some thread is not able to get the time for running,
    * yeh tab hota ha jab saari threads ek saath aajati ha, pr jab hum fair ko true karte ha then woh threads
    * baari baari aati ha, and then starvation ki condition hi nahi aati.
    * */
    private final Lock unfairLock = new ReentrantLock(true); // made fair to true.

    public void accessResource() {
        unfairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
        } catch (Exception e) {
            Thread.currentThread().interrupt(); // it's a good practise to write it to tell other threads about the interruption
        } finally {
            unfairLock.unlock();
            System.out.println(Thread.currentThread().getName() + " released the lock.");
        }
    }

    public static void main(String[] args) {
        UnfairLockExample example = new UnfairLockExample();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                example.accessResource();
            }
        };

        Thread thread1 = new Thread(task, "Thread 1");
        Thread thread2 = new Thread(task, "Thread 2");
        Thread thread3 = new Thread(task, "Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
