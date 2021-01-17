package com.wangq.case3.juc;

/**
 * @author songdan
 * @Created 2021-01-17 15:22
 *
 * 可重入锁：可重复可递归调用的锁，在外层使用锁之后，在内层仍可以使用锁，并且不会发生死锁，这样的锁就叫可重入锁
 *
 * 在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 */
public class ReEnterLockDemo {
    static Object objectLockA = new Object();

    public static void m() {
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "----外层调用");
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "----中层调用");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName() + "\t" + "----内层调用");
                    }
                }
            }
        }, "t1").start();
    }

    public synchronized void m1() {
        System.out.println("======外");
        m2();
    }
    public synchronized void m2() {
        System.out.println("======中");
        m3();
    }
    public synchronized void m3() {
        System.out.println("======内");
    }

    public static void main(String[] args) {
        m();
        ReEnterLockDemo lockDemo = new ReEnterLockDemo();
        lockDemo.m1();
    }
}
