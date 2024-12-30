package lab_8_scd;

public class TestThreadSolution {
    // Three locks
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();
    public static Object Lock3 = new Object();

    public static void main(String args[]) {
        // Create and start the threads
        ThreadDemo1 T1 = new ThreadDemo1();
        ThreadDemo2 T2 = new ThreadDemo2();
        ThreadDemo3 T3 = new ThreadDemo3();
        T1.start();
        T2.start();
        T3.start();
    }

    // Thread 1: Acquires locks in the order: Lock1 -> Lock2 -> Lock3
    private static class ThreadDemo1 extends Thread {
        public void run() {
            synchronized (Lock1) {
                System.out.println("Thread 1: Holding lock 1...");
                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("Thread 1: Holding lock 1 & 2...");
                    synchronized (Lock3) {
                        System.out.println("Thread 1: Holding lock 1, 2 & 3...");
                    }
                }
            }
        }
    }

    // Thread 2: Acquires locks in the order: Lock1 -> Lock2 -> Lock3 (Same order as Thread 1)
    private static class ThreadDemo2 extends Thread {
        public void run() {
            synchronized (Lock1) {  // Lock1 first for Thread 2 as well
                System.out.println("Thread 2: Holding lock 1...");
                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("Thread 2: Holding lock 1 & 2...");
                    synchronized (Lock3) {
                        System.out.println("Thread 2: Holding lock 1, 2 & 3...");
                    }
                }
            }
        }
    }

    // Thread 3: Acquires locks in the order: Lock1 -> Lock2 -> Lock3 (Same order as Thread 1 and Thread 2)
    private static class ThreadDemo3 extends Thread {
        public void run() {
            synchronized (Lock1) {  // Lock1 first for Thread 3 as well
                System.out.println("Thread 3: Holding lock 1...");
                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                synchronized (Lock2) {
                    System.out.println("Thread 3: Holding lock 1 & 2...");
                    synchronized (Lock3) {
                        System.out.println("Thread 3: Holding lock 1, 2 & 3...");
                    }
                }
            }
        }
    }
}
