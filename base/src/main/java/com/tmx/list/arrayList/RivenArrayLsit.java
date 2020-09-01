package com.tmx.list.arrayList;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created By Riven on 2020-8-27
 */
public class RivenArrayLsit implements Serializable {

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
    public void add(Object object) {
        // 1.判断容量是否大于elementData容量
        if (size == this.elementData.length) {
            int newCapacity = size + (size >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
        // 2.使用下标进行赋值
        this.elementData[size++] = object;
    }

    // 获取元素
    public Object get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("数组越界：" + i);
        }
        return this.elementData[i];
    }

    // 重新填入数据
    public void set(int i, Object o) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("数组越界：" + i);
        }
        elementData[i] = o;
    }

    // 获取当前数组长度
    public int getSize() {
        return size;
    }
}
