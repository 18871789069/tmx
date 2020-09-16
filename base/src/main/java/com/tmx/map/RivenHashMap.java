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
        rivenMap.put("10号", "9号");
        rivenMap.put("11号", "9号");
        rivenMap.put("12号", "9号");
        rivenMap.put("13号", "9号");
        rivenMap.put("14号", "9号");
        rivenMap.put("15号", "15222222号");
        rivenMap.put("16号", "16号");
        rivenMap.put("17号", "16号");
        rivenMap.put("18号", "15222222号");
        rivenMap.put("19号", "15222222号");
        rivenMap.put("20号", "15222222号");
        rivenMap.put("21号", "15222222号");
        rivenMap.put("37号", "dddddd");
        rivenMap.put("37号", "222222222");
        System.out.println("=========移除前");
        rivenMap.print();
        String remove = rivenMap.remove("10号");
        System.out.println(remove);
        System.out.println("=========移除后");
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
