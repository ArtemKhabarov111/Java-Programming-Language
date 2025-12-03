import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class ParallelMonteCarloPi {
    static AtomicLong insideCircle = new AtomicLong(0);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int threads = Integer.parseInt(args[0]);
        long iterations = 1_000_000_000 / threads;

        Thread[] threadArray = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            threadArray[i] = new Thread(() -> {
                long result = findPiByMonteCarlo(iterations);
                insideCircle.addAndGet(result);
            });
            threadArray[i].start();
        }

        for (Thread thread : threadArray) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        double pi = 4.0 * insideCircle.get() / (iterations * threads);

        System.out.println("PI is " + pi);
        System.out.println("THREADS " + threads);
        System.out.printf(Locale.US, "ITERATIONS %,d%n", iterations * threads);
        System.out.println("TIME " + (endTime - startTime) + "ms\n");
    }

    public static long findPiByMonteCarlo(long iterations) {
        long inside = 0;

        for (long i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (x * x + y * y <= 1) {
                inside++;
            }
        }
        return inside;
    }
}