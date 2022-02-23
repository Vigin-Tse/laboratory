package com.vg.basic.volatileTest;

/**
 * 测试 volatile 对 引用类型 的影响
 * @author: xieweij
 * @time: 2022/1/26 11:30
 */
public class VCase2 {

    class Cobj{
        String name;
        boolean isRunnable = true;

        public Cobj(String name){
            this.name = name;
        }
    }

    class Cobj2{
        int a = 0;
        volatile int arr[] = {0};
        Cobj c = null;

        public void doInit(){
            this.a = 1;
            this.arr[0] = 1;
            this.c = new Cobj("小明");
        }
    }

    Cobj cobj = new Cobj("O1");
//    volatile Cobj cobj = new Cobj("O1");

    Cobj2 cobj2 = new Cobj2();
//    volatile Cobj2 cobj2 = new Cobj2();

    public static void main(String[] args) throws InterruptedException {

        VCase2 v = new VCase2();

//        for(int i = 1; i <= 16; i++){
//            int finalI = i;
            new Thread(()->{
                //空转
//                while(v.cobj2.a == 0 && v.cobj2.arr[0] == 0 && v.cobj2.c == null){}
//                while(v.cobj.isRunnable){
                    //JMM规定，线程获取Lock，需要将清空工作内存中共享变量的值，从主存中重新获取。
                    // 而释放锁前，需要将自身变量值同步回主存
                    // 如System.out.println();
//                    synchronized (""){
//
//                    }
//                    boolean a = v.cobj.isRunnable;
//                    System.out.println(a);
//                }

//                try {
//                    Thread.sleep(5000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                //volatile保证可见性不仅局限于其修饰的变量，还包括了线程中使用的其他变量。具体是
                //
                //　　1. 读取volatile变量时，在该变量之后的变量也将从主存中重新读取（在volatile变量读操作发生之后的变量，因为禁止了指令重排序，所以是可见的）
                //
                //　　2. 写入volatile变量时，在该变量之前的变量产生的修改也将写入到主存中（在volatile变量写操作发生之前的变量，因为禁止了指令重排序，所以是可见的）
                boolean kk = false;
                boolean aa = false;
                boolean ab = false;
                boolean ac = false;

                int ii = 10;
                int jj = 10;
                VCase2.Cobj ll = new VCase2().new Cobj("");
                while((kk = v.cobj.isRunnable) || (aa = ((ii = v.cobj2.a) == 0)) || (ab = ((jj = v.cobj2.arr[0]) == 0)) || (ac = ((ll = v.cobj2.c) == null))){


                    System.out.println(kk+ "-" + aa + "-" + ab + "-" + ac);
                    System.out.println(ii + "-" + jj + "-" + ll);
                    System.out.println("==========================");
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("t退出了，v.cobj.isRunnable = " + v.cobj.isRunnable);
                System.out.println(kk+ "-" + aa + "-" + ab + "-" + ac);
                System.out.println(ii + "-" + jj + "-" + ll);
                System.out.println("=============last==========");
            }).start();
//        }

        Thread.sleep(3000L);
//        v.cobj2.doInit();
        v.cobj.isRunnable = false;
        v.cobj2.doInit();
        System.out.println("通知退出，v.cobj.isRunnable = " + v.cobj.isRunnable);

//        Thread.sleep(3000L);
//        new Thread(()->{
//            v.cobj2.doInit();
//        });
//        v.cobj2.doInit();
//        System.out.println("停止退出");


    }
}
