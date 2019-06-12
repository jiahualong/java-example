package cc.stan.example.async;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class TestCompletableFuture {

    private Random random = new Random();

    //sleep时间
    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //根据product生成随机价格
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() + product.charAt(1);
    }

    //同步的获取产品价格
    private double getrice(String product) {
        return calculatePrice(product);
    }

    //异步获取futurePrice
    private Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    @Test
    public void testAsyncFunction() throws InterruptedException, ExecutionException, TimeoutException {
        long start = System.nanoTime();

        Future<Double> futurePrice = getPriceAsync("shop");

        System.out.println("f1, " + (System.nanoTime() - start) / 1_000_000);

        double price = futurePrice.get(2, TimeUnit.SECONDS);

        System.out.printf("price %.2f%n", price);
        System.out.println("f2, " + (System.nanoTime() - start) / 1_000_000);
    }

    //异步获取futurePrice，会抛出异常
    private Future<Double> getPriceAsyncHasException(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                throw new RuntimeException("不可用");
                //futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }

    @Test
    public void testAsyncFunctionHasException() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Double> doubleFuture = getPriceAsyncHasException("shop");
        doubleFuture.get(2, TimeUnit.SECONDS);
    }

}
