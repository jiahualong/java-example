package cc.stan.example.async;

import org.junit.Test;

import java.util.concurrent.*;

public class Test1 {
    /**
     * 使用Java5提供的Future接口.
     * Java 8 in action 11.1 Future interface
     */
    @Test
    public void testFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Double> future = executorService.submit(
                new Callable<Double>() {
                    @Override
                    public Double call() {
                        return doSomeLongComputation();
                    }
                }
        );
        System.out.println("final");
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private Double doSomeLongComputation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0.1;
    }

    /**
     * 使用Lambda简化代码
     */
    @Test
    public void testFuture2() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(() -> doSomeLongComputation());
        System.out.println("Final");
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用方法调用简化代码
     */
    @Test
    public void testFuture3() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> future = executorService.submit(this::doSomeLongComputation);
        System.out.println("Final");
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
