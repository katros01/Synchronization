class SynchronizedCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public void decrement() {
        synchronized (this) {
            count--;
        }
    }

    public synchronized int getCount() {
        return count;
    }

    public static void runSynchronizedCounter() throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                counter.decrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
