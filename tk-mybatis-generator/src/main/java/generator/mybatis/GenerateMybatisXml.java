/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : GenerateMybatisXml.java
 * @author : wangyp
 * @date : 2020/4/2 下午2:39
 * @version  1.0
 */

package generator.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class GenerateMybatisXml extends BaseInfo {

    private final String path = absPath + "\\src\\main\\resources\\mapper\\";

    /**
     * 获取表对应的所有列
     *
     * @param
     * @return
     */
    public void getTableColumns(String tableName) {
        try {
            ResultSet resultSetColumn = dbMetaData.getColumns(null, null,
                    tableName, null);
            // 表名
            String className = getFormatString(tableName, true);
            // 文件名
            String fileName = className + "Mapper";
            // 接口地址
            String thisFileName = className + "Mapper";
            String packageNameMybatisDao = "com.bpg.bigdataanalysis.ees.mapper;";
            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                    + "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" "
                    + "\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" + "<mapper namespace=\""
                    + packageNameMybatisDao.substring(0, packageNameMybatisDao.length() - 1) + "." + thisFileName
                    + "\">\n\n" + "\n</mapper>";
            this.outputToFile(fileName + ".xml", header, path);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GenerateMybatisXml() {
        dbMetaData = getDbMetaData();
    }
}
