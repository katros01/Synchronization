import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockCounter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition canIncrement = lock.newCondition();
    private final Condition canDecrement = lock.newCondition();


    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (count >= 10) {
                System.out.println("Count is too high.");
                canIncrement.await();
            }
            count++;
            System.out.println("Incremented count: " + count);
            canDecrement.signal();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (count <= 0) {
                System.out.println("Count is too low.");
                canDecrement.await();
            }
            count--;
            System.out.println("Decremented count: " + count);
            canIncrement.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public static void runLockCounter() throws InterruptedException {
        LockCounter counter = new LockCounter();

        Thread incrementThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    counter.increment();
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread decrementThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    counter.decrement();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        incrementThread.start();
        decrementThread.start();
        incrementThread.join();
        decrementThread.join();

        System.out.println("Final count: " + counter.getCount());
    }
}
