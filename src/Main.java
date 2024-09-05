//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Synchronized Demonstration:");
        SynchronizedCounter.runSynchronizedCounter();

        // -------------------------------------------------

        System.out.println("\nAtomic Variable Demonstration:");
        AtomicVariable.runAtomicVariable();

        // -------------------------------------------------

        System.out.println("\nLock and condition Demonstration:");
        LockCounter.runLockCounter();
    }
}