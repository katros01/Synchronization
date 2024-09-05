import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {
    private static final AtomicInteger atomicCount = new AtomicInteger(0);

    public static void runAtomicVariable() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicCount.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                atomicCount.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final count: " + atomicCount.get());
    }
}
