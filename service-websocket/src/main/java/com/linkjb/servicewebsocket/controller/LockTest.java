package com.linkjb.servicewebsocket.controller;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test
 * @Description TODO
 * @Author shark
 * @Data 2020/1/6 14:33
 **/
public class LockTest {
    private Lock lock = new ReentrantLock();
    private void method(Thread thread){
//        lock.lock();
//        try {
//            System.out.println("线程名"+thread.getName() + "获得了锁");
//        }catch(Exception e){
//            e.printStackTrace();
//        } finally {
//            System.out.println("线程名"+thread.getName() + "释放了锁");
//            lock.unlock();
//        }

        if(lock.tryLock()){
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                System.out.println("线程名"+thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println("我是"+Thread.currentThread().getName()+"有人占着锁，我就不要啦");
        }
    }
    public static void main(String[] args){
        LockTest lockTest = new LockTest();

        //线程1
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                lockTest.method(Thread.currentThread());
            }
        }, "t2");

        t1.start();
        t2.start();
    }




}
