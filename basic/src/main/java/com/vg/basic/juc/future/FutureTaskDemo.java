package com.vg.basic.juc.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable、FutureTask 简单使用
 * @author: xieweij
 * @time: 2021/11/2 16:41
 */

//1.创建caller，处理线程执行逻辑并返回结果
class Caller implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come in Callable");
        Thread.sleep(3000);
        return Thread.currentThread().getName() + " out";
    }
}

class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //2.创建Future（实现runabled的run方法）实例，把callable加入执行
        FutureTask<String> task = new FutureTask<String>(new Caller());

        //3.创建Thread对象，传入FutureTask
        Thread t1 = new Thread(task, "T1");

        //4.启动线程
        t1.start();
//        Thread.sleep(1000);

        while (!task.isDone()){}
        System.out.println(task.get());

        //无效，futureTask只能单个线程（当 state = NEW）执行一次，执行完后final state == NORMAL
        Thread t2 = new Thread(task, "T2");
        t2.start();

        while (!task.isDone()){}
        System.out.println(task.get());
    }

}



