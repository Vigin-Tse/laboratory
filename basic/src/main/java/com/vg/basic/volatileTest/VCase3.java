package com.vg.basic.volatileTest;

/**
 * @author: xieweij
 * @time: 2022/1/27 15:17
 */
public class VCase3 {

    class FinalObj{

        String name;
        int age;
        boolean isRunnable;

        public FinalObj(boolean isRunnable){
            this.isRunnable = isRunnable;
            this.name = "小明";
            this.age = 13;
        }
    }

    final FinalObj fo = new FinalObj(true);

    public static void main(String[] args) throws InterruptedException {
        VCase3 v = new VCase3();

        new Thread(()->{

            while(v.fo.isRunnable){
                //空转
            }

            System.out.println("t退出了，v.fo.isRunnable = " + v.fo.isRunnable);
        }).start();

        Thread.sleep(3000L);

        v.fo.isRunnable = false;
        System.out.println("通知退出，v.fo.isRunnable = " + v.fo.isRunnable);
    }
}
