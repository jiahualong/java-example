package cc.stan.example.async;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.stream.Collectors.toList;

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

    /**
     * 使用CompletableFuture对象的supplayAsync工厂方法创建异步
     *
     * @param product
     * @return
     */
    private Future<Double> getPriceAsyncUseFactory(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    @Test
    public void testAsync2() throws InterruptedException, ExecutionException, TimeoutException {
        Future<Double> price = getPriceAsyncUseFactory("shop");
        System.out.println(price.get(2, TimeUnit.SECONDS));
    }


    class Shop {

        String name;

        public Shop() {

        }

        public Shop(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {

            Future<Double> price = getPriceAsyncUseFactory(name);
            Double pPrice = null;
            try {
                pPrice = price.get(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            return pPrice;
        }
    }

    /**
     * 依次调用5个shop 总计50810ms
     */
    @Test
    public void testListShop() {
        List<Shop> shops = Arrays.asList(
                new Shop("shop1"),
                new Shop("shop2"),
                new Shop("shop3"),
                new Shop("shop4"),
                new Shop("shop5")
        );
        Long start = System.nanoTime();
        System.out.println(
                shops.stream().map(shop -> String.format("%s price is %.2f", shop.name, shop.getPrice()))
                        .collect(toList())
        );
        System.out.println("use:" + (System.nanoTime() - start) / 1_000_000);
    }


}
