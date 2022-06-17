package com.vg.basic.exvg;

//import com.vg.ext.VgServiceImplInvoke;

/**
 * 使用线程上下文加载器打破双亲委派模型
 * @author: xieweij
 * @time: 2022/1/18 10:03
 */
public class ExMain {
    public static void main(String[] args){

//        ExV c;
//
//        VgServiceImpl vgService = new VgServiceImpl();
//        vgService.setService(c = new ExV());
//        vgService.doService();
//
//        System.out.println("ExV:" + c.getClass().getClassLoader());
//        System.out.println("VgServiceImpl:" + vgService.getClass().getClassLoader());
        System.out.println("com.vg.basic.exvg.ExMain：" + Thread.currentThread().getContextClassLoader());
//        VgServiceImplInvoke invoke = new VgServiceImplInvoke();
//        invoke.doService();



//        System.out.println("com.vg.basic.exvg.VgServiceImplInvoke：" + invoke.getClass().getClassLoader());
    }
}
