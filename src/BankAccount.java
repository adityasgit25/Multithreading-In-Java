import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;

    private final Lock lock = new ReentrantLock();

//    // doesn't matter you put sleep also in this method, if we put synchronised, so only one thread can access at a time.
//    public synchronized void withdraw(int amount) {
//        System.out.println(Thread.currentThread().getName() + " attempting to  withdraw " + amount);
//        if (balance >= amount) {
//            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawals");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//            }
//            balance -= amount;
//        } else {
//            System.out.println(Thread.currentThread().getName() + " insufficient balance");
//        }
//    }

    // using locks
        public void withdraw(int amount) {
            System.out.println(Thread.currentThread().getName() + " attempting to  withdraw " + amount);
            // tryLock() is used to lock if the lock is present else just return false, it also have some waiting
            // time function as well

            try {
                if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    if (balance >= amount) {
                        try {
                            System.out.println(Thread.currentThread().getName() + " proceeding with the transaction");
                            Thread.sleep(3000);
                            balance -= amount;
                            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                        } catch (Exception e) {

                        } finally {
                            lock.unlock();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " Insufficient balance");
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try later");
                }
            } catch (Exception e) {
                System.out.println("Insufficient balance");
            }

        }

}
