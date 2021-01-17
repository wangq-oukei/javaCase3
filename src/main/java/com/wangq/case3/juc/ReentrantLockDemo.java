package com.wangq.case3.juc;

import sun.jvm.hotspot.runtime.Threads;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author songdan
 * @Created 2021-01-17 16:19
 */
public class ReentrantLockDemo {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() ->{

            lock.lock();
            lock.lock();
            try{
                System.out.println("======外层");
                lock.lock();
                try{
                    System.out.println("====中层");
                    lock.lock();
                    try{
                        System.out.println("=====内层");
                    }catch(Exception ex){

                    }finally{
                        lock.unlock();
                    }
                }catch(Exception ex){

                }finally{
                    lock.unlock();
                }
            }catch(Exception ex){

            }finally{
                lock.unlock();
                // lock.unlock();
            }
        }, "t1").start();

        new Thread(() ->{

            lock.lock();
            try{
                System.out.println("======外层");
                lock.lock();
            }catch(Exception ex){

            }finally{
                lock.unlock();
            }
        }, "t2").start();

    }
}
