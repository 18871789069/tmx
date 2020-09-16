package com.tmx.map;

import java.io.Serializable;

/**
 * Created By Riven on 2020-8-27
 */
public class RivenHashMap<K,V> implements RivenMap<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    // 默认空间大小 16
    private int DEFAULT_INITIAL_CAPACITY = 1 << 3; // aka 16

    private float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;

    private Node<K, V>[] table = null;

    public RivenHashMap() {

    }

    @Override
    public V put(K key, V value) {
        // 判断数组对象是否为空
        if (table == null) {
            table = new Node[DEFAULT_INITIAL_CAPACITY];
        }
        // 只做一次扩容处理
        if (size > DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR) {
            resize();
        }
        // 获取链表存储位置
        int index = getIndex(key, table.length);
        // node处理
        Node<K, V> node = table[index];
        if (node == null) {
            node = new Node(key, value, null);
            size++;
        } else {
            // 链表存在，需要查看是否存在键，存在则替换，否则新增
            Node<K, V> first = node;
            while (first != null) {
                if (first.key.equals(key) || first.getKey() == key) {
                    return first.setValue(value);
                } else {
                    // 利用newNode进行处理，不改变node的值
                    if (first.next == null) {
                        node = new Node(key, value, node);
                        size++;
                    }
                    first = first.next;
                }
            }
        }
        table[index] = node;
        return null;
    }

    public V remove(K key) {
        // 先找到数组位置
        Node<K, V> node = removeNode(key);
        return node == null ? null : node.value;
    }

    /**
     * 对象不是new出来的就会公用一个存储空间
     * kvNode 对象即 table[index]
     * 遍历kvNode且对kvNode的子节点赋值修改就是对table[index]进行修改
     * @param key
     * @return
     */
    private Node<K, V> removeNode(K key) {
        int index = getIndex(key, table.length);
        // 数组的当前node
        Node<K, V> kvNode = table[index];
        Node<K, V> node = kvNode;
        while (node != null) {
            Node<K, V> next = node.next;
            if (node.key.equals(key)) {
                size--;
                if (kvNode == node) {
                    table[index] = next;
                } else {
                    kvNode.next = next;
                }
                return node;
            }
            kvNode = node;
            node = next;
        }
        return node;
    }

    private int getIndex(K key, int length) {
        int hash = key.hashCode();
        return hash % length;
    }

    // 扩容处理
    private void resize() {
        // 创建新的数组
        Node<K, V>[] newTable = new Node[DEFAULT_INITIAL_CAPACITY << 1];
        // 遍历老数组
        for (int i = 0; i < table.length; i++) {
            Node<K, V> oldNode = table[i];
            while (oldNode != null) {
                table[i] = null;
                K key = oldNode.key;
                // 计算新的下标
                int index = getIndex(key, newTable.length);
                Node<K, V> oldNodeNext = oldNode.next;
                // 判断newTable数组中,是否存在下标相同，如果下标相同则存放在原来的.next
                oldNode.next = newTable[index];
                // 先将oldNode的第一个节点赋值
                newTable[index] = oldNode;
                oldNode = oldNodeNext;
            }
        }
        table = newTable;
        DEFAULT_INITIAL_CAPACITY = DEFAULT_INITIAL_CAPACITY << 1;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node<K, V> getNode(K key) {
        // 获取node所在的数组位置
        int index = getIndex(key, table.length);
        Node<K, V> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Node<K, V> implements Entry<K, V> {

        private K key;

        private V value;

        private Node<K, V> next;

        public Node() {

        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        };
    }

}
