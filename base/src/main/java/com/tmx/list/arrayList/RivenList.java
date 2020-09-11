package com.tmx.list.arrayList;

/**
 * Created By Riven on 2020-9-3
 */
public interface RivenList<E> {

    void add(E e);

    E get(int i);

    E remove(int i);

    void set(int i, E e);
}
