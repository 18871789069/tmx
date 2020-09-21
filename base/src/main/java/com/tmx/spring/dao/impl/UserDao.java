package com.tmx.spring.dao.impl;

import com.tmx.spring.dao.IUserDao;

/**
 * Created By Riven on 2020-9-21
 */
public class UserDao implements IUserDao {
    @Override
    public void add() {
        System.out.println("静态代理部分");
    }
}
