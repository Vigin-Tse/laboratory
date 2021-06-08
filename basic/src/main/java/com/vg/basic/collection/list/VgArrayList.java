package com.vg.basic.collection.list;

import java.io.Serializable;
import java.util.*;

/**
 * @Description
 * @Author xieweij
 * @create 2020/6/12 8:55
 */
public class VgArrayList<E>
        extends AbstractList<E> //继承AbstractList抽象类
        implements List<E>      //实现List接口
                  /*
                   标志接口，表明实现这个这个接口的 List 集合是支持快速随机访问的。
                   也就是说，实现了这个接口的集合是支持 快速随机访问 策略的。
                   如果是实现了这个接口的 List，那么使用for循环的方式获取数据会优于用迭代器获取数据。
                   */
                 , RandomAccess
                 /*
                   标记接口，只有实现这个接口后，然后在类中重写Object中的clone方法，然后通过类调用clone方法才能克隆成功。
                   如果不实现这个接口，则会抛出CloneNotSupportedException(克隆不被支持)异常。
                 */
                 , Cloneable
                 /*
                  标记接口，可支持序列化
                 */
                 , Serializable {

    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 默认初始长度
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 用于初始化长度为0的List
     */
    private static final Object[] EMPTY_ELEMENT_DATA= {};

    /**
     * 默认，用于初始化不指定长度list
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    /**
     * 底层数组，用于存放实际元素
     * transient：序列化的时候，此字段是不会被序列化的。
     */
    transient Object[] elementData;

    /**
     * 记录实际元素（个数）长度(非数组长度)
     */
    private int size = 0;

    /***
     * 数组（集合）的最大长度
     */
    private static final int ARRAY_MAX_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 无参构造方法
     */
    public VgArrayList(){
        //初始化空数组
        this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    /**
     * 有参构造方法
     * 指定elementData数组长度，不允许初始化长度小于0，否者抛出异常
     * @param initialCapacity 指定长度
     */
    public VgArrayList(int initialCapacity){
        if (initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        }else if (initialCapacity == 0){
            this.elementData = EMPTY_ELEMENT_DATA;
        }else{
            throw new IllegalArgumentException("无效长度" + initialCapacity);
        }
    }

    /**
     * 当传递的参数为集合类型时，会把集合类型转化为数组类型，并赋值给elementData。
     * @param c
     */
    public VgArrayList(Collection<? extends E> c){
        //把集合转化为数组
        this.elementData = c.toArray();

        //判断数组元素长度
        if ((size = this.elementData.length) != 0){

            //判断是否成功转化为Object类型数组
            if(this.elementData.getClass() != Object[].class){
                // 不为Object数组的话就进行复制
                this.elementData = Arrays.copyOf(this.elementData, size, Object[].class);
            }
        }else{
            //传入集合为空，把数组赋值为空数组
            this.elementData = EMPTY_ELEMENT_DATA;
        }
    }



    /**
     * 获取指定下标元素值
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        //检查下标
        rangCheck(index);
        //返回指定下标元素值，先下转型
        return (E) this.elementData[index];
    }

    /**
     * 设定指定下标元素值
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        //检查下标
        rangCheck(index);

        //获取旧值，向下转型
        E oldValue = (E) this.elementData[index];

        //设置新值
        this.elementData[index] = element;

        //返回旧值
        return oldValue;
    }

    /**
     * 获取元素长度
     * @return
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * 数组尾部插入值
     * @param e
     * @return
     */
    @Override
    public boolean add(E e){
        //确保数组容量
        this.ensureCapacityInternal(size +1);
        //元素插入数组尾部
        elementData[size++] = e;
        return true;
    }

    /**
     * 指定下标插入值（插入完size会加1）
     * @param index
     * @param e
     */
    @Override
    public void add(int index, E e){
        //检查index
        this.rangCheckForAdd(index);
        //确保数组容量
        this.ensureCapacityInternal(size +1);
        //元素从插入位置往后移动
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        //插入新元素
        elementData[index] = e;
        size++;
    }

    /**
     * remove函数用户移除指定下标的元素，此时会把指定下标到数组末尾的元素向前移动一个单位，并且会把数组最后一个元素设置为null，
     * 这样是为了方便之后将整个数组不被使用时，会被GC，可以作为小的技巧使用。
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        //检查索引合法性
        this.rangCheck(index);

        //操作数加1
        this.modCount++;

        //获取旧值（要被移除的元素）
        E oldValue = (E) this.elementData[index];

        //需要移动的元素的个数
        int numMoved = size - index - 1;
        if (numMoved > 0){
            System.arraycopy(this.elementData, index + 1, this.elementData, index, numMoved);
        }

        // 赋值为空，有利于进行GC
        elementData[--size] = null;

        return oldValue;
    }

    /**
     * 从头开始查找与指定元素相等(两个引用对象指向同一对象)的元素。
     * 注意：是可以查找null元素的，意味着ArrayList中可以存放null元素的。
     * 与此函数对应的lastIndexOf，表示从尾部开始查找。
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o){
        // 查找的元素为空
        if (o == null){
            // 遍历数组，找到第一个为空的元素，返回下标
            for(int i = 0; i < size; i++){
                if (this.elementData[i] == null){
                    return i;
                }
            }
        }else{// 查找的元素为空
            for(int i = 0; i < size; i++){
                // 遍历数组，找到第一个和指定元素相等的元素，返回下标
                if (o.equals(this.elementData[i])){
                    return i;
                }
            }
        }
        //没有找到，返回空
        return -1;
    }

    /**
     * 确保数组容量
     * @param minCapacity
     */
    private void ensureCapacityInternal(int minCapacity){
        //计算数组（增加元素）需要的最小容量
        minCapacity = calculateCapacity(elementData, minCapacity);
        //判断数组容量是否足够，不过则扩容
        ensureExplicitCapacity(minCapacity);
    }

    /**
     * 判断数组容量是否足够，不过则扩容
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity){
        //操作数加一
        modCount++;

        //如果所需的（最小）容量大于数组实际容量，扩容
        if (minCapacity > elementData.length){
            //扩容
            this.grow(minCapacity);
        }

    }

    /**
     * 扩容：
     * 正常情况下会扩容1.5倍，特殊情况下（新扩展数组大小已经达到了最大值）则只取最大值。
     */
    private void grow(int minCapacity){

        //获取数组现有容量
        int oldCapacity = elementData.length;

        //num>>n:相当于num/（2的n次方），算数右移
        //num<< n:相当于 num×(2的n次方)，算数左移（逻辑左移）
        //数组扩容为现有1.5倍，
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity < minCapacity){
            newCapacity = minCapacity;
        }

        //判断扩容后是否超出最大值
        if (newCapacity > ARRAY_MAX_SIZE){
            //判断是否因为溢出
            if (minCapacity < 0){
                throw new OutOfMemoryError();
            }
            newCapacity = (minCapacity > ARRAY_MAX_SIZE) ? Integer.MAX_VALUE : ARRAY_MAX_SIZE;
        }
        //原有数组复制至（扩容后的）新数组
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public static void main(String[] args){

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE+2);
    }

    /**
     *计算数组（增加元素）需要的最小容量
     * @param elementData 数组
     * @param minCapacity 所需的最小容量
     * @return
     */
    private static int calculateCapacity(Object[] elementData, int minCapacity){
        //判断当前数组是空数组
        if(elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA){
            //如果所需最小容量小于10，则直接返回默认所需长度（10）
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }


    private void rangCheckForAdd(int index){
        if (index > this.size || index <0){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
    }

    private void rangCheck(int index){
        if (index >= this.size || index < 0){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
    }
}
