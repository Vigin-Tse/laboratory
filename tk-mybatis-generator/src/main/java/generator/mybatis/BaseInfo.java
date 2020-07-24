/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : BaseInfo.java
 * @author : wangyp
 * @date : 2020/4/2 上午8:28
 * @version  1.0
 */

package generator.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BaseInfo {

    public final String url = "jdbc:mysql://localhost:3306/bpg?serverTimezone=Hongkong";
    protected final String username = "bpguser";
    protected final String password = "root";

    protected final String driverClass = "com.mysql.cj.jdbc.Driver";
    protected DatabaseMetaData dbMetaData = null;

    /**
     * DTO 包名
     */
    protected final String packageNameDto = "com.bpg.bigdataanalysis.ees.dto;";

    /**
     * service 包名
     */
    protected final String packageNameService = "com.bpg.bigdataanalysis.ees.service;";

    /**
     * mapper接口包名
     */
    protected final String packageNameMybatis = "com.bpg.bigdataanalysis.ees.mapper";

    /**
     * 项目绝对路径
     */
    protected String absPath;

    {
        try {
            absPath = (new File("")).getCanonicalPath();
        } catch (IOException e) {
            log.error("获取项目路径错误{}", e.getMessage());
        }
    }

    /**
     * 获取数据库连接，并且返回数据库对象
     *
     * @param
     * @return
     */
    protected DatabaseMetaData getDbMetaData() {
        Connection conn;
        try {
            Class.forName(driverClass);
            conn = DriverManager.getConnection(url, username, password);
            dbMetaData = conn.getMetaData();
        } catch (Exception e) {
            log.error("连接数据库失败：{}", e.getMessage());
        }
        return dbMetaData;
    }

    /**
     * 获取所有的表
     *
     * @param pattern 表名匹配模式
     * @return 所有表名
     */
    public List<String> getAllTableList(String pattern) {
        List<String> tableList = new ArrayList<String>();
        try {
            String[] types = {"TABLE"};
            ResultSet rs = dbMetaData.getTables(null, null, pattern, types);
            while (rs.next()) {
                // 表名
                String tableName = rs.getString("TABLE_NAME");
                tableList.add(tableName);
            }
        } catch (SQLException e) {
            log.debug("获取表名失败：{}", e.getMessage());
        }
        return tableList;
    }

    /**
     * 处理字符串，去掉下划线“_”，并且把下划线的下一个字符变大写，flag为true，表示首字母要大写
     *
     * @param name
     * @param flag
     * @return
     */
    protected String getFormatString(String name, boolean flag) {
        int preStrLen = 4;
        name = name.toLowerCase();
        String[] nameTemp = name.split("_");
        StringBuilder builder = new StringBuilder();
        for (String str : nameTemp) {
            String head = str.substring(0, 1).toUpperCase();
            String tail = str.substring(1);
            builder.append(head).append(tail);
        }
        StringBuilder result = null;
        if (!flag) {
            result = new StringBuilder();
            String head = builder.substring(0, 1).toLowerCase();
            String tail = builder.substring(1);
            result.append(head).append(tail);
            return result.toString();
        }
        return builder.substring(3).toString();
    }

    /**
     * 把String内容写到文件
     *
     * @param fileName 文件名
     * @param content  内容
     * @param path     生成文件路径
     */
    protected void outputToFile(String fileName, String content, String path) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(path + fileName);
        } catch (FileNotFoundException e1) {
            log.error("文件路径不正确，找不到文件：{}", e1.getMessage());
        }
        byte[] b = content.getBytes();
        try {
            os.write(b);
            os.flush();
        } catch (IOException e) {
            log.error("生成文件失败：{}", e.getMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                log.error("关闭流失败：{}", e.getMessage());
            }
        }
    }

}
