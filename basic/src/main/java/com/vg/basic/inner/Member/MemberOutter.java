package com.vg.basic.inner.Member;

/**
 * @Description 成员内部类，成员内部类是最普通的内部类，它的定义为位于另一个类的内部
 * @Author xieweij
 * @create 2020/6/10 9:07
 */
public class MemberOutter {

    private String var1 = "outter-private-var1";

    private MemberInner inner;

    public void getVar1(){
        System.out.println(var1);
    }

    public MemberInner getInstance(){
        if (inner == null){
            inner = new MemberInner();
        }
        return inner;
    }

    /**
     * 在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问
     */
    public void outterCaller(){
        MemberInner inner = new MemberInner();

        //1.外部类可访问内部类私有成员变量
        System.out.println(inner.var3);

        //2.外部类可访问内部类私有方法
        inner.getVar3();
    }

    public class MemberInner {

        private String var3 = "inner-private-var3";

        public String var4 = "inner-public-var4";

        private void getVar3(){
            System.out.println(var3);
        }

        public void getVar4(){
            System.out.println(var4);
        }

        /**
         * 成员内部类可以 无条件 访问 外部类 的 所有 成员属性 和 成员方法（包括private成员和静态成员）。
         *
         * 不过要注意的是，当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员。
         * 如果要访问外部类的同名成员，需要以下面的形式进行访问：
         * 外部类.this.成员变量
         * 外部类.this.成员方法
         */
        public void innerCaller(){
            System.out.println(var1);
            getVar1();
        }
    }
}

