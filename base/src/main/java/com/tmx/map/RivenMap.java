package com.tmx.map;

/**
 * Created By Riven on 2020-9-13
 */
public interface RivenMap<K, V> {

    // 向集合中插入数据
    public V put(K key, V value);

    // 根据k 从Map集合中查询元素
    public V get(K key);

    // 获取集合元素个数
    public int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);
    }
}
