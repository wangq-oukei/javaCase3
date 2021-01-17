package com.wangq.case3.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author songdan
 * @Created 2021-01-17 17:11
 */
public class LockSupportDemo {

    static Object objectLock = new Object();
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+ "\t" + "----被唤醒");
        }, "A");
        threadA.start();
/*        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Thread threadB = new Thread(() -> {
            LockSupport.unpark(threadA);
            System.out.println(Thread.currentThread().getName()+ "\t" + "----执行唤醒通知");
        }, "B");
        threadB.start();


        //lockAwaitSignal();
        //synchronizedWaitNotify();
    }

    private static void lockAwaitSignal() {
        new Thread(() -> {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t" + "----被唤醒");
            }catch(Exception ex){

            }finally{
                lock.unlock();
            }
        }, "L1").start();

        new Thread(() -> {
            lock.lock();
            try{
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t" + "----执行唤醒通知");
            }catch(Exception ex){

            }finally{
                lock.unlock();
            }
        }, "L2").start();
    }


    private static void synchronizedWaitNotify() {
        Object obj = new Object();
        new Thread(() ->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            }
        }, "t1").start();

        new Thread(() ->{
            synchronized (obj) {
                obj.notify();
                System.out.println(Thread.currentThread().getName() + "\t" + "----执行唤醒通知");

            }
        }, "t2").start();
    }
}
