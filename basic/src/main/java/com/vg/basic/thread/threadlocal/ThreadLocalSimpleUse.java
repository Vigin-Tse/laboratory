package com.vg.basic.thread.threadlocal;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/10 11:04
 */
public class ThreadLocalSimpleUse {

    /**
     * ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal，其为每个使用该变量的线程提供独立的变量副本，
     * 不同线程只能从中get，set，remove自己的变量，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     * 从线程的角度看，目标变量就象是线程的本地变量，这也是类名中“Local”所要表达的意思。
     */
    private static final ThreadLocal<Object> THREAD_LOCAL = new ThreadLocal<Object>(){
        /**
         * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
         */
        @Override
        protected Object initialValue() {
            System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
            return null;
        }
    };


    public static void main(String[] args){
        new Thread(()->{
            THREAD_LOCAL.get();
            THREAD_LOCAL.set("VALUE-A");
            System.out.println("线程A：设值完毕");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程A：" + THREAD_LOCAL.get());
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            THREAD_LOCAL.get();
            THREAD_LOCAL.set("VALUE-B");
            System.out.println("线程B：设值完毕");

            System.out.println("线程B：" + THREAD_LOCAL.get());
        }).start();
    }

}
