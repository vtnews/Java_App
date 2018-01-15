package com.mufeng.Futrue;

import java.util.concurrent.*;

/**
 * Created by mufeng on 2017/12/23.
 */
public class FutrueDemo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            public Double call() {
                return doSomeLongComputation();
            }
        });
        doSomethingElse();
        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            // 计算抛出一个异常
        } catch (InterruptedException ie) {
            // 当前线程在等待过程中被中断
        } catch (TimeoutException te) {
            // 在Future对象完成之前超过已过期
        }
    }

    private static void doSomethingElse() {

    }

    private static Double doSomeLongComputation() {
        return null;
    }
}
