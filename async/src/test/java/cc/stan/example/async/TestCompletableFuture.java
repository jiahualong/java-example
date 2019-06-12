package cc.stan.example.async;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class TestCompletableFuture {
    private Random random = new Random();

    @Test
    public void test1() {

        long start = System.nanoTime();

        Future<Double> futurePrice = getPriceAsync("shop");

        System.out.println("f1, " + (System.nanoTime() - start) / 1_000_000);

        try {
            double price = futurePrice.get(2, TimeUnit.SECONDS);
            System.out.printf("price %.2f%n", price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println("f2, " + (System.nanoTime() - start) / 1_000_000);
        //TODO: Java in action 8 getPriceAsync
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

    //同步的获取产品价格
    private double getrice(String product) {
        return calculatePrice(product);
    }

    //根据product生成随机价格
    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() + product.charAt(1);
    }

    //sleep时间
    private void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
