package com.tmx.spring.service.impl;

import com.tmx.spring.dao.UserDao;
import com.tmx.spring.service.UserService;
import com.tmx.spring.util.TransactionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created By Riven on 2020-9-21
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TransactionUtil transactionUtil;

    @Autowired
    private UserDao userDao;

    /**
     * try catch不会把事务反馈到AOP
     */
    @Override
    public void add() {
        userDao.add("1","111","123","riven", 18);
        int i = 1 / 1;
        userDao.add("2","222","123","riven", 18);
//        try {
//            userDao.add("1","111","123","riven", 18);
//            int i = 1 / 0;
//            userDao.add("2","222","123","riven", 18);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 手动事务配置方法
     */
//    @Override
//    public void add() {
//        TransactionStatus transactionStatus = null;
//        try {
//            transactionStatus = transactionUtil.begin();
//            userDao.add("1","111","123","riven", 18);
//            System.out.println("新增成功第一条");
//            int i = 1 / 1;
//            userDao.add("2","222","123","riven", 18);
//            if (transactionStatus != null)
//                transactionUtil.commit(transactionStatus);
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (transactionStatus != null)
//                transactionUtil.rollback(transactionStatus);
//        }
//    }

    @Override
    public void del() {
        System.out.println("删除用户！");
    }
}
