package com.mufeng.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mufeng on 2018/1/29.
 */
public class ConditionUseCase {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void conditionWait() throws InterruptedException {
        lock.lock();
        try {
            condition.await();//1.释放锁，开始等待其他线程
            //3.获取锁，执行后面逻辑
        } finally {
            lock.unlock();
        }
    }
    public void conditionSignal() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();//2.释放锁，通知等待的线程
        } finally {
            lock.unlock();
        }
    }
}
