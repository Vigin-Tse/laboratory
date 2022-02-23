package com.vg.basic.volatileTest;

/**
 * @author: xieweij
 * @time: 2022/2/21 14:52
 */
public class VCase4 {
//    private String years;
//    private String months;
//    private volatile String days;
////        private int days;
//
//
//    public void update() throws InterruptedException {
//        this.years  = new String("1");
//        this.days   = new String("2");
//        this.months = new String("3");
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        VCase4 time = new VCase4();
//
//        time.update();
//    }

    private volatile static VCase4 instance;

    public static VCase4 getInstance() {
        if (instance == null) {
            synchronized (VCase4.class) {
                if (instance == null) {
                    instance = new VCase4();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        VCase4.getInstance();
    }
}
