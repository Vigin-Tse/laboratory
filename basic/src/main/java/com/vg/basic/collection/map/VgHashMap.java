package com.vg.basic.collection.map;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Description
 * @Author xieweij
 * @create 2020/6/19 13:41
 */
public class VgHashMap<K, V> extends AbstractMap<K, V>
        implements Map<K, V>, Cloneable, Serializable {

    /**
     * 链表结点，继承自Map.Entry
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V>{

        final int hash;
        final K key;
        V value;
        Node<K, V> next;


        Node(int hash, K key, V value, Node<K, V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return this.key;
        }

        @Override
        public final V getValue() {
            return this.value;
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = this.value;
            this.value = newValue;
            return oldValue;
        }

        @Override
        public final String toString(){
            return this.key + "=" + this.value;
        }

        /**
         * 重写equals方法
         * （注意，hashCode（）方法也要重写）
         * @param o
         * @return
         */
        @Override
        public final boolean equals(Object o){
            if (o == this){//直接对比内存地址
                return true;
            }else{
                if (o instanceof Map.Entry){//先确定是Map.Entry派生对象
                    //向上转型
                    Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                    if (Objects.equals(this.key, e.getKey()) &&
                        Objects.equals(this.value, e.getValue())){
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * 重写hashcode()方法
         * （equals相等，hashcode一定相等）
         * @return
         */
        @Override
        public final int hashCode(){
            return Objects.hashCode(this.key) ^ Objects.hashCode(this.value);
        }
    }

    /**
     * 红黑树结点
     * 原本继承LinkedHashMap.Entry<K,V>
     * @param <K>
     * @param <V>
     */
    static final class TreeNode<K,V>
//            extends LinkedHashMap.Entry<K,V> {
            extends Node<K, V> {

        //继承自LinkedHashMap.Entry<K,V>的属性
        Entry<K,V> before, after;

        //父亲结点
        TreeNode<K, V> parent;  // red-black tree links

        //左儿子
        TreeNode<K, V> left;

        //右儿子
        TreeNode<K, V> right;

        //前方结点
        TreeNode<K, V> prev;    // needed to unlink next upon deletion

        //是否是红色
        boolean red;

        TreeNode(int hash, K key, V val, Node<K, V> next) {
            super(hash, key, val, next);
        }

        /**
         * 从根结点寻找h和k符合的结点
         * @param hash
         * @param k
         * @return
         */
        final TreeNode<K, V> getTreeNode(int hash, Object k){
            return ((parent != null)? root() : this).find(hash, k, null);
        }

        /**
         * 向上查找根节点
         * @return
         */
        final TreeNode<K, V> root(){
            for(TreeNode<K, V> r = this, p;;){
                if ((p = r.parent) == null){
                    return r;
                }
                r = p;
            }
        }

        /**
         * 从根结点p开始根据hash和key值寻找指定的结点。kc是key的class
         * @param hash  目标key的hash值
         * @param k     目标key
         * @param kc    目标key的class类型
         * @return
         */
        final TreeNode<K, V> find(int hash, Object k, Class<?> kc){

            // 1.将p节点赋值为调用此方法的节点，即为红黑树根节点
            TreeNode<K, V> p = this;

            // 2.从p节点开始向下遍历
            do{
                int ph,
                    dir;
                K pk;
                TreeNode<K, V> pl = p.left,
                               pr = p.right,
                               q;

                // 3.如果传入的hash值小于p节点的hash值，则往p节点的左边遍历
                if ((ph = p.hash) > hash){
                    p = pl;

                // 4.如果传入的hash值大于p节点的hash值，则往p节点的右边遍历
                }else if(ph < hash){
                    p = pr;

                // 5.如果传入的hash值和key值等于p节点的hash值和key值,则p节点为目标节点,返回p节点
                }else if((pk = p.key) == k || (k != null && k.equals(pk))){
                    return p;

                // 6.p节点的左节点为空则将向右遍历
                }else if(pl == null){
                    p = pr;

                // 7.p节点的右节点为空则向左遍历
                }else if(pr == null){
                    p = pl;

                // 8.将p节点与k进行比较
                }else if((kc != null ||
                        (kc = comparableClassFor(k)) != null) // 8.1 kc不为空代表k实现了Comparable
                        && (dir = compareComparables(kc, k, pk)) != 0){ // 8.2 k<pk则dir<0, k>pk则dir>0
                    // 8.3 k<pk则向左遍历(p赋值为p的左节点), 否则向右遍历
                    p = (dir < 0) ? pl : pr;

                // 9.代码走到此处, 代表key所属类没有实现Comparable, 直接指定向p的右边遍历
                }else if((q = pr.find(hash, k, kc)) != null){
                    return q;

                // 10.代码走到此处代表“pr.find(h, k, kc)”为空, 因此直接向左遍历
                }else{
                    p = pl;
                }
            }while(p != null);

            return null;
        }

    }

/* ======================================================== hashMap 重要成员属性 ========================================================= */

    /**
     * table就是存储Node类的数组
     */
    transient Node<K, V>[] table;

    /**
     * 记录hashmap中存储键-值对的数量
     */
    transient int size;

    /**
     * hashmap结构被改变的次数，fail-fast机制
     */
    transient  int modCount;

    /**
     * 扩容的门限值，当size大于这个值时，table数组进行扩容
     */
    int threshold;

    final float loadFactor;


    /**
     * 默认容量 = 1 * 2的四次方 = 16
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * 最大容量 = 1 * 2的三十次方
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * 默认负载因子 = 0.75
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 链表转化为红黑树的阈值, 9个节点转
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 红黑树节点转换链表节点的阈值, 6个节点转
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * 转红黑树时, table的最小长度
     */
    static final int MIN_TREEIFY_CAPACITY = 64;


    /**
     * 无参构造
     * 所有值默认
     */
    public VgHashMap(){
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * 有参构造
     * 当知道所要构建的数据容量的大小时，最好直接指定大小，提高效率
     * @param initialCapactiy
     */
    public VgHashMap(int initialCapactiy){
        this(initialCapactiy, DEFAULT_LOAD_FACTOR);
    }

    /**
     * 有参构造
     * 可以自己指定初始容量和装载因子
     * @param initialCapacity
     * @param loadFactor
     */
    public VgHashMap(int initialCapacity, float loadFactor){

        //判断指定容量值有效性
        if (initialCapacity < 0){
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        }

        //超大最大容量，直接初始化hashmap容量为最大值
        if (initialCapacity > MAXIMUM_CAPACITY){
            initialCapacity = MAXIMUM_CAPACITY;
        }

        //判断指定负载因子有效性
        if(loadFactor < 0 || Float.isNaN(loadFactor)){
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        }

        this.loadFactor = loadFactor;
        //重新定义了扩容的门限
        this.threshold = tableSizeFor(initialCapacity);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     *如果x实现了Comparable接口，则返回x的Class
     * @param x
     * @return
     */
    static Class<?> comparableClassFor(Object x){

        // 1.判断x是否实现了Comparable接口
        if (x instanceof Comparable){
            Class<?> c;
            Type[] ts, as;
            Type t;
            ParameterizedType p;

            // 2.校验x是否为String类型
            if((c = x.getClass()) == String.class){
                return c;
            }

            // 3.遍历x实现的所有接口
            if ((ts = x.getClass().getGenericInterfaces()) != null){
                for(int i = 0; i < ts.length; i++){

                    // 4.如果x实现了Comparable接口，则返回x的Class
                    if (((t = ts[i]) instanceof ParameterizedType)
                        && ((p = (ParameterizedType)t).getRawType() == Comparable.class)
                        && (as = p.getActualTypeArguments()) != null
                        && as.length == 1
                        && as[0] == c){

                        return c;
                    }
                }
            }

        }
        return null;
    }

    /**
     * k < x则返回<0； k > x则返回>0
     * @param kc 类类型
     * @param k  key对象（传入）
     * @param x  树结点的key对象
     * @return
     */
    static int compareComparables(Class<?> kc, Object k, Object x){
        return (x == null || x.getClass() != kc ? 0 :
                ((Comparable)k).compareTo(x));
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

/* ======================================================== put()方法 ========================================================= */

//    @Override
//    public V put(K key, V value){
//        return putVal(hash(key), key, value, false, true);
//    }

    /**
     * 计算key的hash值
     * 与（&）、非（~）、或（|）、异或（^）
     *位异或运算（^）,运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
     * @param key
     * @return
     */
    static final int hash(Object key){
        int h;
        // 1.先拿到key的hashCode值; 2.将hashCode的高16位参与运算
        return key == null? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

//    final V putVal(int hash, K key, V value,boolean onlyIfAbsent,
//                   boolean evict){
//
//        Node<K, V> tab[];
//        Node<K, V> p;
//        int n, i;
//
//        if ((tab = this.table) == null || (n = tab.length) == 0){
//            n = (tab = resize()).length;
//        }
//
//        if ((p = tab[i = (n - 1) & hash]) == null){
//            tab[i] = newNode(hash, key, value, null);
//        }else{
//
//        }
//        return null;
//    }

/* ======================================================== get()方法 ========================================================= */

    @Override
    public V get(Object key){
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null? null : e.getValue();
    }

    final Node<K, V> getNode(int hash, Object key){
        Node<K, V>[] tab;
        Node<K, V> first, e;
        int n;
        K k;

        //1. （table数组不为空） && （table长度大于0）
        // &&  table索引位置(使用table.length - 1和hash值进行位与运算)的节点不为空
        if ((tab = table) != null && (n = tab.length) > 0 && (first = tab[(n-1) & hash]) != null){

            // 2.检查first节点的hash值和key是否和入参的一样，如果一样则first即为目标节点，直接返回first节点
            if (first.hash == hash && ((k = first.getKey()) == key || (key != null && key.equals(k)))){
                return first;
            }

            // 3.如果first不是目标节点，并且first的next节点不为空则继续遍历
            if ((e = first.next) != null){

                // 4.如果是红黑树节点，则调用红黑树的查找目标节点方法getTreeNode
                if(first instanceof TreeNode){
                    return ((VgHashMap.TreeNode<K,V>)first).getTreeNode(hash, key);
                }

                do{
                    // 5.执行链表节点的查找，向下遍历链表, 直至找到节点的key和入参的key相等时,返回该节点
                    if (e.hash == hash && ((k = first.getKey()) == key || (key != null && key.equals(k)))){
                        return e;
                    }

                }while ((e = e.next) != null);
            }
        }
        return null;
    }

}
