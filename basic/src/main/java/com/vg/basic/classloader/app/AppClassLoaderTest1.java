package com.vg.basic.classloader.app;

import java.net.URL;
import java.net.URLClassLoader;

public class AppClassLoaderTest1 {

    public static void main(String[] args) throws Exception {
//        AppClassA a = new AppClassA();
//        AppClassB b = new AppClassB();

        AppClassLoaderTest1 test = new AppClassLoaderTest1();

        test.testCase_2();

    }

    public void testCase_1() throws Exception{
        URL classUrl = new URL("file:D:\\github-mine\\laboratory\\basic\\target\\classes\\com\\vg\\basic\\classloader\\app\\");//jvm 类放在位置

//        AppClassA systemClassA = new AppClassA();

//        URLClassLoader loaderA = new URLClassLoader(new URL[]{classUrl});
        URLClassLoader loaderA = new URLClassLoader(new URL[]{classUrl});

        Class classA = loaderA.loadClass("com.vg.basic.classloader.app.AppClassA");

        Object objA = classA.newInstance();
        classA.getMethod("println").invoke(objA);


        System.out.println("AppClassLoaderTest1的类加载器：" + AppClassLoaderTest1.class.getClassLoader());
        System.out.println("classA（Class）对象的类加载器：" + classA.getClassLoader());
    }

    public void testCase_2() throws Exception{
        URL classUrl = new URL("file:D:\\classes\\");//jvm 类放在位置

        URLClassLoader loader_1 = new URLClassLoader(new URL[]{classUrl});
        URLClassLoader loader_2 = new URLClassLoader(new URL[]{classUrl});

        Class classA_1 = loader_1.loadClass("AppClassA");
        Class classA_2 = loader_2.loadClass("AppClassA");


        System.out.println("classA_1的类型：" + classA_1);
        System.out.println("classA_2的类型：" + classA_2);

        System.out.println("classA_1的类加载器：" + classA_1.getClassLoader());
        System.out.println("classA_2的类加载器：" + classA_2.getClassLoader());

        System.out.println("是否同一个对象：" + classA_1.equals(classA_2));
    }
}
