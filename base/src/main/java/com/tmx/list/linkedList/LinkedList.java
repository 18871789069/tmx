package com.tmx.list.linkedList;

/**
 * Created By Riven on 2020-9-4
 */
public class LinkedList<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;

    class Node<E> {
        Node<E> prev;
        E e;
        Node<E> next;
    }

    public void add(E e) {
        Node<E> node = new Node<>();
        node.e = e;
        if (first == null) {
            first = node;
        } else {
            node.prev = last;
            last.next = node;
        }
        last = node;
        size++;
    }

    public void add(int i, E e) {
        Node<E> newNode = new Node<>();
        // 旧节点数据
        Node<E> oldNode = getNode(i);
        Node<E> oldNodePre = oldNode.prev;
        newNode.e = e;
        // 新节点的前一个节点的数据处理
        if (oldNode.prev == null) {
            first = newNode;
        } else {
            // 替换节点的前一个节点的下一个节点更新为新增的节点
            oldNodePre.next = newNode;
            // 新节点的上一位是老节点的前一个节点
            newNode.prev = oldNodePre;
        }
        // 新增的下一个节点就是旧节点
        newNode.next = oldNode;
        // 老节点的前一个节点为新增的节点
        oldNode.prev = newNode;
        size++;
    }

    public E get(int index) {
        LinkedList<E>.Node<E> node = getNode(index);
        return (E) node.e;
    }

    public Node getNode(int index) {
        Node node = null;
        if (first != null) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }
}
