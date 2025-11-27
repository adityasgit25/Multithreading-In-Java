public class Test {
    public static void main(String[] args) {
        Counter counter = new Counter(); // shared resource
        MyThread t1 = new MyThread(counter);
        MyThread t2 = new MyThread(counter);
        t1.start();
        t2.start();

        /*

        * ❌ join() does NOT mean t1 completes fully before t2 starts.
          ❌ join() does NOT control execution order.
          ❌ join() does NOT prevent threads from running at the same time.

          ✔ join() only makes the main thread wait until t1 and t2 finish.
          *
        * */
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {

        }
        System.out.println(counter.getCount());
    }
}