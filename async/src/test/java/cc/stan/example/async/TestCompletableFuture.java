package cc.stan.example.async;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

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

        String productName;

        public Shop(String productName) {
            this.productName = productName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public Double getPrice() {

            Future<Double> price = getPriceAsyncUseFactory(productName);
            Double pPrice = null;
            try {
                pPrice = price.get(3, TimeUnit.SECONDS);
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
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone")
        );
        Long start = System.nanoTime();
        System.out.println(
                shops.stream().map(shop -> String.format("%s price is %.2f", shop.productName, shop.getPrice()))
                        .collect(toList())
        );
        System.out.println("use:" + (System.nanoTime() - start) / 1_000_000);
    }

    /**
     * 使用parallelStream 2078
     */
    @Test
    public void testUseParallelStream() {
        List<Shop> shops = Arrays.asList(
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone")
        );
        Long start = System.nanoTime();
        System.out.println(
                shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.productName, shop.getPrice()))
                        .collect(toList())
        );
        System.out.println("use:" + (System.nanoTime() - start) / 1_000_000);

    }

    /**
     * 顺序执行
     * use:5104
     */
    @Test
    public void testA() {
        List<Shop> shops = Arrays.asList(
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone")
        );

        Long start = System.nanoTime();


        List<String> result = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getProductName(), shop.getPrice())))
                .map(CompletableFuture::join).collect(toList());
        System.out.println(result);
        System.out.println("use:" + (System.nanoTime() - start) / 1_000_000);
    }

    /**
     * 异步执行
     * use:2097
     */
    @Test
    public void testR() {
        List<Shop> shops = Arrays.asList(
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone")
        );

        Long start = System.nanoTime();

        List<CompletableFuture<String>> list = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getProductName(), shop.getPrice())))
                .collect(toList());

        List<String> result = list.stream().map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println(result);
        System.out.println("use:" + (System.nanoTime() - start) / 1_000_000);
    }

    @Test
    public void cpu() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    static final Executor executor = Executors.newFixedThreadPool(Math.min(5, 100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    /**
     * use:1089
     */
    @Test
    public void testUseExecutor() {
        List<Shop> shops = Arrays.asList(
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone")
        );

        Long start = System.nanoTime();

        List<CompletableFuture<String>> list = shops.stream().map(shop ->
                CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getProductName(), shop.getPrice()), executor))
                .collect(toList());

        List<String> result = list.stream().map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println(result);
        System.out.println("use:" + (System.nanoTime() - start) / 1_000_000);
    }


    /**
     * CompletableFuture 比 parallelStream 优点
     * 可以对Executor进行配置，尤其是线程池大小
     */
    @Test
    public void test() {

        List<Shop> shops = Arrays.asList(
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone"),
                new Shop("iphone")
        );


    }


}
