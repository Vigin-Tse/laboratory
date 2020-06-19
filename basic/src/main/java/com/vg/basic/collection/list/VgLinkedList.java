package com.vg.basic.collection.list;

import java.util.*;

/**
 * @Description
 * @Author xieweij
 * @create 2020/6/16 11:21
 */
public class VgLinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>
                 , Deque<E>//Deque接口表示是一个双端队列，那么也意味着LinkedList是双端队列的一种实现，所以，基于双端队列的操作在LinkedList中全部有效
                 , Cloneable//可拷贝
                 , java.io.Serializable{//可序列化

    /**
     * 静态成员内部类
     * 内部类Node就是实际的结点，用于存放实际元素的地方。
     * @param <E>
     */
    private static class Node<E>{

        E item;    //数据域
        Node prev; //前驱
        Node next; //后继

        /**
         * 构造函数
         * @param prev
         * @param item
         * @param next
         */
        public Node(Node<E> prev, E item, Node<E> next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * 结点（实际元素）个数（长度）
     */
    transient int size = 0;

    /**
     * 头结点
     */
    transient Node<E> first;

    /**
     * 尾结点
     */
    transient Node<E> last;

    public VgLinkedList(){}

    /**
     * 会调用无参构造函数，并且会把集合中所有的元素添加到LinkedList中。
     * @param list
     */
    public VgLinkedList(Collection<? extends E> list){
        //调用w无参构造方法
        this();
        //添加集合中所有元素
        addAll(list);
    }

    /**
     * 添加一个元素（尾端）
     * 向LinkedList中添加一个元素，并且添加到链表尾部。具体添加到尾部的逻辑是由linkLast函数完成的。
     * @param e
     * @return
     */
    @Override
    public boolean add(E e){
        this.linkedLast(e);
        return true;
    }

    /**
     * 把集合装换添加到链表尾部
     * @param list
     * @return
     */
    @Override
    public boolean addAll(Collection<? extends E> list){
        return this.addAll(size, list);
    }

    /**
     * 把集合装换添加到链表指定位置
     * @param index
     * @param list
     * @return
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> list){
        //检查索引合法性
        this.checkPositionIndex(index);

        //把集合转化为数组
        Object[] objects = list.toArray();

        //获取集合大小
        int newArrayLength = objects.length;
        //如果要添加的集合为空
        if (newArrayLength == 0){
            return false;
        }

        //创建暂存结点, 前驱：pred，后继:succ
        Node<E> pred, succ;

        //判断是否尾部插入
        if (index == size){
            pred = this.last;
            succ = null;
        }else{
            succ = this.node(index);
            pred = succ.prev;
        }

        for (Object o : objects){
            //向下转型
            E e = (E)o;
            Node<E> node = new Node<E>(pred, e, null);

            if (pred == null){
                this.first = node;
            }else{
                pred.next = node;
            }
            pred = node;
        }

        if (succ == null){
            this.last = pred;
        }else{
            pred.next = succ;
            succ.prev = pred;
        }

        this.modCount++;
        this.size = size + newArrayLength;
        return true;
    }

    /**
     * 添加一个元素到链表尾端
     * @param e
     */
    void linkedLast(E e){

        //保存当前链表的尾结点，l为fianl类型，不可更改
        final Node<E> l = this.last;

        //创建当前（新）元素结点，前驱指向当前链表的尾结点
        Node<E> newNode = new Node<E>(l, e, null);

        //把新结点变为链表的尾结点
        this.last = newNode;

        if (first == null){
            //头结点为空，说明当前插入元素为第一个元素
            this.first = newNode;
        }else{
            //（原）尾结点后继指向新节点
            l.next = newNode;
        }
        //结构性（操作数）+1
        this.modCount++;
        //链表（大小）长度+1
        size++;
    }

    /**
     * 删除元素
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> n = this.first; n != null; n = n.next) {
                if (n.item == null) {
                    this.unlink(n);
                    return true;
                }
            }
        } else {
            for (Node<E> n = this.first; n != null; n = n.next) {
                if (o.equals(n.item)) {
                    this.unlink(n);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将指定的结点从链表中断开，不再累赘。
     * @param e
     * @return
     */
    public E unlink(Node<E> e){
        Node<E> pred = e.prev;
        Node<E> next = e.next;
        E element = e.item;

        if (pred == null){
            this.first = next;
        }else{
            pred.next = next;
            e.prev = null;
        }

        if (next == null){
            this.last = pred;
        }else{
            next.prev = pred;
            e.next = null;
        }

        e.item = null;

        this.modCount++;
        this.size--;

        return element;
    }

    /**
     * 根据下标，返回结点
     * @param index
     * @return
     */
    public Node<E> node(int index){

        //分两半搜索索引位置
        if (index < (size >> 1)){
            //从前搜素
            Node<E> x = this.first;
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            return x;
        }else{
            //从后搜索
            Node<E> x = this.last;
            for (int i = size - 1; i > index; i--){
                x = x.prev;
            }
            return x;
        }
    }

    /**
     * 检查索引合法性
     * @param index
     */
    private void checkPositionIndex(int index){
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
    }


    // ====================================
    // AbstractSequentialList 相关方法实现
    // ====================================

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    // ====================================
    // AbstractCollection 相关方法实现
    // ====================================

    @Override
    public int size() {
        return 0;
    }


    // ====================================
    // Deque 相关方法实现
    // ====================================

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
