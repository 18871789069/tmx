package com.tmx.list.arrayList;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created By Riven on 2020-8-27
 */
public class RivenArrayLsit<E> implements RivenList<E>, Serializable {

    private static final long serialVersionUID = 1L;

    // 数组对象
    private Object[] elementData;

    // 默认初始化容量为10
    private static final int DEFAULT_CAPACITY = 10;

    // 记录RivenArrayLsit实际大小
    private int size;

    // 默认初始化构造函数，容量为10
    public RivenArrayLsit() {
        this(DEFAULT_CAPACITY);
    }

    // 自定义容量初始化函数
    public RivenArrayLsit(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("RivenArrayList数组初始化不应该小于0：" + initialCapacity);
        } else if (initialCapacity == 0) {
            this.elementData = new Object[0];
        } else {
            this.elementData = new Object[initialCapacity];
        }
    }

    // 添加元素
    @Override
    public void add(E e) {
        // 1.判断容量是否大于elementData容量
        ensureExplicitCapacity(size + 1);
        // 2.使用下标进行赋值
        this.elementData[size++] = e;
    }

    public void add(int index, E e) {
        ensureExplicitCapacity(size + 1);
        int numMoved = size - index;
        System.arraycopy(elementData, index, elementData, index + 1, numMoved);
        elementData[index] = e;
        size++;
    }

    // 获取元素
    @Override
    public E get(int i) {
        rangeCheck(i);
        return (E) this.elementData[i];
    }

    // 重新填入数据
    @Override
    public void set(int i, E e) {
        rangeCheck(i);
        elementData[i] = e;
    }

    private void ensureExplicitCapacity(int miniCapacity) {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + oldCapacity >> 1;
            if (newCapacity - miniCapacity < 0)
                newCapacity = miniCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    // 获取当前数组长度
    public int getSize() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("数组越界：" + index);
    }

    // 根据索引删除对象
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E e = get(index);
        int numMoved = size - index - 1;
        // 往前移动的数组
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;
        return e;
    }

    public boolean remove(E e) {
        if (e == null) {
            for (int i = 0; i < size; i++) {
                E value = get(i);
                if (value == null)
                    remove(i);
                return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                E value = get(i);
                if (e.equals(value))
                    remove(i);
                return true;
            }
        }
        return false;
    }
}
