package com.tmx.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于ArrayList实现HashMap
 * Created By Riven on 2020-9-11
 */
public class ArrayRivenMap<K,V> {

    List<Entry<K,V>> entryList = new ArrayList<>();

    public void put(K key,V value) {
        Entry<K,V> entry = getEntry(key);
        if (entry != null) {
            entry.value = value;
        } else {
            Entry<K,V> newEntry = new Entry<>(key, value);
            entryList.add(newEntry);
        }
    }

    public V get(K key) {
        Entry<K,V> entry = getEntry(key);
        return entry == null ? null: entry.value;
    }

    private Entry<K,V> getEntry(K key) {
        for (int i = 0; i < entryList.size(); i++) {
            Entry<K, V> kvEntry = entryList.get(i);
            if (kvEntry.key.equals(key)) {
                return kvEntry;
            }
        }
        return null;
    }

    // 帮助类
    class Entry<K,V> {
        K key;
        V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        ArrayRivenMap<String, String> arrayRivenMap = new ArrayRivenMap<>();
        arrayRivenMap.put("a","aaaaa");
        arrayRivenMap.put("b","bbbbb");
        System.out.println(arrayRivenMap.get("c"));
    }
}






