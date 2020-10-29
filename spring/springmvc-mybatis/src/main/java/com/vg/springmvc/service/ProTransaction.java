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

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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


    @Autowired
    private DataSource dataSource;

    /**
     * 编程式事物提交
     */
    public void inserUser(){

        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            this.userMapper.insertUser(new SysUserModel("小一"));
            this.userMapper.insertUser(new SysUserModel("小二"));
//            int i = 1/0;
            dataSourceTransactionManager.commit(transactionStatus);
        }catch (Exception e){
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transactionStatus);
        }
    }

    /**
     * jdbc提交
     * @throws SQLException
     */
    public void deleteUser() throws SQLException {
        Connection connection = this.dataSource.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        try {
            statement.execute("delete from sys_user where name = '小一';");
            statement.execute("delete from sys_user where name = '小二';");
            int i = 1/0;
            connection.commit();
            System.out.println("提交");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                System.out.println("回滚");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            statement.close();
            connection.close();
        }

    }
}
