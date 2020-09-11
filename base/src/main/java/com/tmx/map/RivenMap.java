package com.tmx.map;

import java.io.Serializable;

/**
 * Created By Riven on 2020-8-27
 */
public class RivenMap<K,V> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 默认空间大小 16
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    public RivenMap() {

    }
}
