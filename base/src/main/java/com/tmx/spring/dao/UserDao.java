package com.tmx.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created By Riven on 2020-9-21
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(String id, String account, String password, String name, Integer age) {
        String sql = "INSERT INTO tr_user(id, account, password, name, age) VALUES(?,?,?,?,?);";
        int updateResult = jdbcTemplate.update(sql, id, account, password, name, age);
        System.out.println("updateResult:" + updateResult);
    }
}
