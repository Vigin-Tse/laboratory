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

    /**
     * 负载因子 默认0.75
     * */
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
        //设置默认负载因子
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
        //计算阀值
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
/*
    @Override
    public V put(K key, V value){
        return putVal(hash(key), key, value, false, true);
    }
*/
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
/*
    final V putVal(int hash, K key, V value,boolean onlyIfAbsent, boolean evict){

        Node<K, V> tab[];
        Node<K, V> p;
        int n, i;

        // 1.校验table是否为空或者length等于0，如果是则调用resize方法进行初始化
        if ((tab = this.table) == null || (n = tab.length) == 0){
            n = (tab = resize()).length;
        }

        // 2.通过hash值计算索引位置，将该索引位置的头节点赋值给p，如果p为空则直接在该索引位置新增一个节点即可
        if ((p = tab[i = (n - 1) & hash]) == null){

            tab[i] = newNode(hash, key, value, null);
        }else{

            // table表该索引位置不为空，则进行查找
            Node<K, V> e;
            K k;

            // 3.判断p节点的key和hash值是否跟传入的相等，如果相等, 则p节点即为要查找的目标节点，将p节点赋值给e节点
            if(p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))){
                e = p;

            // 4.判断p节点是否为TreeNode, 如果是则调用红黑树的putTreeVal方法查找目标节点
            }else if(p instanceof TreeNode){
                e = ((TreeNode<K, V>)p).putTreeVal(this, tab, hash, key, value);

            // 5.走到这代表p节点为普通链表节点，则调用普通的链表方法进行查找，使用binCount统计链表的节点数
            }else{
                for(int binCount = 0; ; ++binCount){
                    // 6.如果p的next节点为空时，则代表找不到目标节点，则新增一个节点并插入链表尾部
                    if ((e = p.next) == null){
                        p.next = newNode(hash, key, value, null);
                        // 7.校验节点数是否超过8个，如果超过则调用treeifyBin方法将链表节点转为红黑树节点，
                        // 减一是因为循环是从p节点的下一个节点开始的
                        if (binCount >= TREEIFY_THRESHOLD - 1){
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    // 8.如果e节点存在hash值和key值都与传入的相同，则e节点即为目标节点，跳出循环
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))){
                        break;
                    }
                    p = e;
                }
            }

            // 9.如果e节点不为空，则代表目标节点存在，使用传入的value覆盖该节点的value，并返回oldValue
            if (e != null){
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null){
                    e.value = value;
                }
                // 用于LinkedHashMap
                afterNodeAccess(e);
                return oldValue;
            }
        }

        ++modCount;

        // 10.如果插入节点后节点数超过阈值，则调用resize方法进行扩容
        if(++size > threshold){
            resize();
        }
        // 用于LinkedHashMap
        afterNodeInsertion(evict);
        return null;
    }
*/


    /**
     * 新增数组上的链表结点
     * @param hash
     * @param key
     * @param value
     * @param next
     * @return
     */
    Node<K, V> newNode(int hash, K key, V value, Node<K, V> next){
        return new Node<>(hash, key, value, next);
    }

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

/* ======================================================== resize ()方法 ========================================================= */
/*
    final Node<K, V>[] resize(){
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null)? 0 : oldTab.length;
        //阈值
        int oldThr = threshold;
        int newCap, newThr = 0;

        // 1.老表的容量不为0，即老表不为空
        if (oldCap > 0) {
            // 1.1 判断老表的容量是否超过最大容量值：如果超过则将阈值设置为Integer.MAX_VALUE，并直接返回老表,
            // 此时oldCap * 2比Integer.MAX_VALUE大，因此无法进行重新分布，只是单纯的将阈值扩容到最大
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            // 1.2 将newCap赋值为oldCap的2倍，如果newCap<最大容量并且oldCap>=16, 则将新阈值设置为原来的两倍
            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                newThr = oldThr << 1;
            }
        // 2.如果老表的容量为0 并且 老表的阈值大于0, 是因为初始容量被放入阈值，则将新表的容量设置为老表的阈值
        }else if(oldThr > 0){
            newCap = oldThr;
        }else{
            // 3.老表的容量为0, 老表的阈值为0，这种情况是没有传初始容量的new方法创建的空表，将阈值和容量设置为默认值
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        }

        // 4.如果新表的阈值为空, 则通过新的容量*负载因子获得阈值
        if (newThr == 0){
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        // 5.将当前阈值设置为刚计算出来的新的阈值，定义新表，容量为刚计算出来的新容量，将table设置为新定义的表。
        threshold = newThr;

        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        // 6.如果老表不为空，则需遍历所有节点，将节点赋值给新表
        if (oldTab != null){
            for(int j=0; j < oldCap; ++j){
                Node<K,V> e;
                // 将索引值为j的老表头节点赋值给e
                if ((e = oldTab[j]) != null){
                    // 将老表的节点设置为空, 以便垃圾收集器回收空间
                    oldTab[j] = null;
                    // 7.如果e.next为空, 则代表老表的该位置只有1个节点，计算新表的索引位置, 直接将该节点放在该位置
                    if(e.next == null){
                        newTab[e.hash & (newCap - 1)] = e;

                    // 8.如果是红黑树节点，则进行红黑树的重hash分布(跟链表的hash分布基本相同)
                    }else if (e instanceof TreeNode){
                        ((TreeNode<K,V>) e).split(this, newTab, j, oldCap);

                    // 9.如果是普通的链表节点，则进行普通的重hash分布
                    }else{

                        // 存储索引位置为:“原索引位置”的节点
                        Node<K,V> loHead = null, loTail = null;

                        // 存储索引位置为:“原索引位置+oldCap”的节点
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do{
                            next = e.next;
                            // 9.1 如果e的hash值与老表的容量进行与运算为0,则扩容后的索引位置跟老表的索引位置一样
                            if ((e.hash & oldCap) == 0){
                                // 如果loTail为空, 代表该节点为第一个节点
                                if(loTail == null){
                                    // 则将loHead赋值为第一个节点
                                    loHead = e;
                                }else{
                                    // 否则将节点添加在loTail后面
                                    loTail.next = e;
                                }
                                // 并将loTail赋值为新增的节点
                                loTail = e;

                            // 9.2 如果e的hash值与老表的容量进行与运算为1,则扩容后的索引位置为:老表的索引位置＋oldCap
                            }else{
                                // 如果hiTail为空, 代表该节点为第一个节点
                                if(hiTail == null){
                                    // 则将hiHead赋值为第一个节点
                                    hiHead = e;
                                }else{
                                    // 否则将节点添加在hiTail后面
                                    hiTail.next = e;
                                }
                                // 并将hiTail赋值为新增的节点
                                hiTail = e;
                            }
                        }while ((e = next) != null);
                        // 10.如果loTail不为空（说明老表的数据有分布到新表上“原索引位置”的节点），则将最后一个节点
                        // 的next设为空，并将新表上索引位置为“原索引位置”的节点设置为对应的头节点
                        if (loTail != null){
                            loTail.next = null;
                            newTab[j] = loHead;
                        }

                        // 11.如果hiTail不为空（说明老表的数据有分布到新表上“原索引+oldCap位置”的节点），则将最后
                        // 一个节点的next设为空，并将新表上索引位置为“原索引+oldCap”的节点设置为对应的头节点
                        if(hiTail != null){
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
*/
}
