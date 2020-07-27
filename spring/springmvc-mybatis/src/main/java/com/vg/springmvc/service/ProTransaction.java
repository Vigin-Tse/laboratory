package com.vg.springmvc.service;

import com.vg.springmvc.mapper.SysUserMapper;
import com.vg.springmvc.model.SysUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 编程式事务 demo
 */
@Service
public class ProTransaction {

    @Autowired
    private SysUserMapper userMapper;

    //spring的事务管理器
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //spring事务默认配置
//    @Autowired
//    private DefaultTransactionDefinition transactionDefinition;

//    @Transactional
    public void inserUser(){
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            this.userMapper.insertUser(new SysUserModel("小一"));
            this.userMapper.insertUser(new SysUserModel("小二"));
            int i = 1/0;
            dataSourceTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
        }

    }
}
