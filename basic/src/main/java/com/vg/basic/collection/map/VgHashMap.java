package com.vg.basic.collection.map;

import java.io.Serializable;
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

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
