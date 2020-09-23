package com.tmx.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * Created By Riven on 2020-9-22
 */
@Component
@Scope("prototype") // 解决单例访问现成会出现线程问题，多例
public class TransactionUtil {

    private TransactionStatus transactionStatus;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    // 开启事务
    public TransactionStatus begin() {
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    // 提交事务
    public void commit(TransactionStatus transactionStatus) {
        if (transactionStatus != null)
            dataSourceTransactionManager.commit(transactionStatus);
    }

    // 回滚事务
    public void rollback() {
        if (transactionStatus != null)
            dataSourceTransactionManager.rollback(transactionStatus);
    }
}
