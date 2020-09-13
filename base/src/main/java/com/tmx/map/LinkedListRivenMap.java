package com.tmx.map;

import java.util.LinkedList;

/**
 * 基于linkedList手写Map
 * Created By Riven on 2020-9-11
 */
public class LinkedListRivenMap<K,V> {

    LinkedList<Entry<K,V>>[] tables = new LinkedList[996];

    private int size;

    public void put(K key, V value) {
        Entry<K,V> newEntry = new Entry<>(key, value);
        int hash = getHash(key);
        LinkedList<Entry<K,V>> linkedList = tables[hash];
        // 如果链表数据为空，则新创建一个链表添加进数组
        if (linkedList == null) {
            LinkedList<Entry<K,V>> newEntryList = new LinkedList<>();
            newEntryList.add(newEntry);
            tables[hash] = newEntryList;
        } else {
            // hashCode 相同情况下 存放在链表后面
            for (Entry entry: linkedList) {
                // hash相同，key相同则替换
                if (entry.key.equals(key)) {
                    entry.value = value;
                } else {
                    // hash相同，key不同则新增
                    linkedList.add(entry);
                }
            }
        }
        size++;
    }

    public V get(K key) {
        Entry<K,V> entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    public Entry getEntry(K key) {
        int hash = getHash(key);
        LinkedList<Entry<K, V>> entryLinkedList = tables[hash];
        if (entryLinkedList != null) {
            for (Entry entry: entryLinkedList) {
                if (entry.key.equals(key)) {
                    return entry;
                }
            }
        }
        return null;
    }

    // hash值
    public int getHash(Object key) {
        int hashCode = key.hashCode();
        int hash = hashCode % tables.length;
        return hash;
    }

    public int getSize() {
        return size;
    }

    class Entry<K,V> {
        K key;
        V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LinkedListRivenMap<String, String> linkedListRivenMap = new LinkedListRivenMap<>();
        linkedListRivenMap.put("a", "aaaaaaa");
        linkedListRivenMap.put("b", "bbbbbbb");
        linkedListRivenMap.put("c", "ccccccc");
        linkedListRivenMap.put("b", "ddddddd");
        System.out.println(linkedListRivenMap.get("a"));
        System.out.println(linkedListRivenMap.get("b"));
        System.out.println(linkedListRivenMap.get("c"));
    }
}
