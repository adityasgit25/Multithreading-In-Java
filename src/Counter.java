public class Counter {
    private int count =0;

    public void increment() {

        // this is called synchronised block, we can make the method as well synchronised.
        // this part of the program is called critical section.
        // when we are not using synchronised, it's called a race condition, both the threads are accessing the same counter.
        // basically called as mutual exclusion.
        synchronized (this) { // this refers to the current instance, and so in one time, only one thread can access this.
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
