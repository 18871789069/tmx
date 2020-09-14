package com.tmx.map;

import java.io.Serializable;

/**
 * Created By Riven on 2020-8-27
 */
public class RivenHashMap<K,V> implements RivenMap<K, V>, Serializable {

    private static final long serialVersionUID = 1L;

    // 默认空间大小 16
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

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
        // 扩容处理
        // 获取链表存储位置
        int hash = key.hashCode();
        int index = hash % table.length;
        // node处理
        Node<K, V> node = table[index];
        if (node == null) {
            node = new Node(key, value, null);
            size++;
        } else {
            // 链表存在，需要查看是否存在键，存在则替换，否则新增
            Node<K, V> newNode = node;
            while (newNode != null) {
                if (newNode.key.equals(key) || newNode.getKey() == key) {
//                    newNode.value = value;
//                    return null;
                    return newNode.setValue(value);
                } else {
                    // 利用newNode进行处理，不改变node的值
                    if (newNode.next == null) {
                        node = new Node(key, value, node);
                        size++;
                    }
                    newNode = newNode.next;
                }
            }
        }
        table[index] = node;
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.value;
    }

    private Node<K, V> getNode(K key) {
        // 获取node所在的数组位置
        int hash = key.hashCode();
        int index = hash % table.length;
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

    void print() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("这是第【" + i +"】个数组位置：");
            Node<K, V> kvNode = table[i];
            while (kvNode != null) {
                System.out.print("[key:"+kvNode.key+";value:"+kvNode.value +"]");
                kvNode = kvNode.next;
            }
            System.out.println();
        }
    }

    class Node<K, V> implements Entry<K, V> {

        private K key;

        private V value;

        private Node<K, V> next;

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

    public static void main(String[] args) {
        RivenHashMap<String, String> rivenMap = new RivenHashMap<>();
        rivenMap.put("1号", "aaaaaa");
        rivenMap.put("2号", "2222");
        rivenMap.put("3号", "33333");
        rivenMap.put("4号", "dddddd");
        rivenMap.put("5号", "2222");
        rivenMap.put("6号", "33333");
        rivenMap.put("7号", "dddddd");
        rivenMap.put("8号", "dddddd");
        rivenMap.put("9号", "9号");
        rivenMap.put("15号", "15号");
        rivenMap.put("16号", "16号");
        rivenMap.put("17号", "16号");
        rivenMap.put("37号", "dddddd");
        rivenMap.put("37号", "222222222");
        rivenMap.print();
//        System.out.println(rivenMap.get("1号"));
//        System.out.println(rivenMap.get("2号"));
//        System.out.println(rivenMap.get("3号"));
//        System.out.println(rivenMap.get("4号"));
//        System.out.println(rivenMap.get("5号"));
//        System.out.println(rivenMap.get("6号"));
//        System.out.println(rivenMap.get("7号"));
//        System.out.println(rivenMap.get("8号"));
//        System.out.println(rivenMap.get("9号"));
        System.out.println(rivenMap.get("15号"));
    }
}
