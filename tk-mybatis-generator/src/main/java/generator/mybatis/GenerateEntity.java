/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : GenerateEntity.java
 * @author : wangyp
 * @date : 2020/4/1 下午3:59
 * @version  1.0
 */

package generator.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class GenerateEntity extends BaseInfo {

    private final String packageName = "com.bpg.bigdataanalysis.ees.dto;";
    private final String path = absPath + "\\src\\main\\java\\" + packageName.replace(".", "\\")
            .substring(0, packageName.length() - 1) + "\\";

    /**
     * 获取表对应的所有列
     *
     * @param tableName 表名
     */
    public void getTableColumns(String tableName) {
        try {
            String accessDomain = "private";
            ResultSet resultSet = dbMetaData.getColumns(null, null, tableName, "%");
            while (resultSet.next()) {
                ResultSet resultSetColumn = dbMetaData.getColumns(null, null, tableName, null);
                String className = getFormatString(tableName, true);
                StringBuilder header = new StringBuilder("package " + packageName + "\n");
                header.append("import com.fasterxml.jackson.annotation.JsonInclude;" + "\n");
                header.append("import lombok.Getter;" + "\n");
                header.append("import lombok.Setter;" + "\n");
                header.append("import javax.persistence.Table;" + "\n");
                header.append("\n");
                header.append("import javax.persistence.GeneratedValue;" + "\n");
                header.append("import javax.persistence.GenerationType;" + "\n");
                header.append("import javax.persistence.Id;" + "\n");
                StringBuilder builder = new StringBuilder();
                builder.append("/**\n" + " * @author xieweij\n" + " */").append("\n");
                builder.append("@Table(name = \"").append(tableName).append("\")").append("\n");
                builder.append("@JsonInclude(JsonInclude.Include.NON_NULL)" + "\n");
                builder.append("@Getter" + "\n");
                builder.append("@Setter" + "\n");
                builder.append("public class ").append(className).append(" {").append("\t\n\n");
                while (resultSetColumn.next()) {
                    String remark = resultSetColumn.getString("REMARKS");
                    builder.append("\t" + "/** ").append("\n");
                    builder.append("\t" + " * ").append(remark).append("\n");
                    builder.append("\t" + " */").append("\n");
                    if (remark.contains("主键")) {
                        builder.append("\t" + "@Id" + "\n");
                        builder.append("\t" + "@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\n");
                    }
                    builder.append("\t").append(accessDomain).append(" ");
                    String columnType = resultSetColumn.getString("TYPE_NAME");
                    String type = getColumnType(columnType);
                    if ("Date".equals(type)) {
                        header.append("import java.util.Date;\n");
                    }
                    builder.append(type).append(" ");
                    String columnName = resultSetColumn.getString("COLUMN_NAME");
                    columnName = this.getFormatString(columnName, false);
                    builder.append(columnName).append(";").append("\n\n");
                }
                builder.append("");
                builder.append("}");
                header.append("\n");
                header.append(builder);
                this.outputToFile(className + ".java", header.toString(), path);
            }
        } catch (SQLException e) {
            log.error("获取表列信息失败：{}", e.getMessage());
        }
    }

    public GenerateEntity() {
        dbMetaData = getDbMetaData();
    }

    /**
     * 数据库类型转为java类型
     *
     * @param column
     * @return
     */
    @SuppressWarnings("DuplicateCondition")
    private String getColumnType(String column) {
        String columnType = null;
        String varchar = "VARCHAR";
        String bigint = "BIGINT";
        String datetime = "DATETIME";
        String unsignInt = "INT UNSIGNED";
        String anInt = "INT";
        String unsignedBigInt = "BIGINT UNSIGNED";
        String unsignedTinyInt = "TINYINT UNSIGNED";
        String decimal = "DECIMAL";
        String aFloat = "FLOAT";
        String aDouble = "DOUBLE";
        String text = "TEXT";
        String mediumtext = "MEDIUMTEXT";
        String longtext = "LONGTEXT";
        String timestamp = "TIMESTAMP";
        String date = "DATE";
        String tinyint = "TINYINT";
        String unsignedDecimal = "DECIMAL UNSIGNED";
        String smallint = "SMALLINT";
        String bit = "BIT";
        String aChar = "CHAR";
        String blob = "BLOB";
        String varbinary = "VARBINARY";
        if (varchar.equals(column)) {
            columnType = "String";
        } else if (bigint.equals(column)) {
            columnType = "Long";
        } else if (datetime.equals(column)) {
            columnType = "Date";
        } else if (anInt.equals(column) || unsignInt.equals(column)) {
            columnType = "Integer";
        } else if (unsignedBigInt.equals(column)) {
            columnType = "Long";
        } else if (unsignedTinyInt.equals(column)) {
            columnType = "Short";
        } else if (decimal.equals(column) || aFloat.equals(column) || aDouble.equals(column)) {
            columnType = "Double";
        } else if (text.equals(column) || mediumtext.equals(column) || longtext.equals(column)) {
            columnType = "String";
        } else if (timestamp.equals(column) || date.equals(column) || datetime.equals(column)) {
            columnType = "Date";
        } else if (tinyint.equals(column)) {
            columnType = "Short";
        } else if (unsignedDecimal.equals(column)) {
            columnType = "Double";
        } else if (smallint.equals(column)) {
            columnType = "Short";
        } else if (bit.equals(column)) {
            columnType = "Short";
        } else if (aChar.equals(column)) {
            columnType = "String";
        } else if (varbinary.equals(column)) {
            columnType = "byte";
        } else if (blob.equals(column)) {
            columnType = "byte";
        }
        return columnType;
    }

    /**
     * 生成get/set方法
     *
     * @param
     * @return
     */
    private String getSetGenerater(String columnName, String columnType) {
        String sb = "\tpublic " + columnType + " get" + columnName.substring(0, 1).toUpperCase()
                + columnName.substring(1, columnName.length()) + "() {\n" + "\t\treturn " + columnName + ";\n"
                + "\t}\n\n" + "\tpublic void set" + columnName.substring(0, 1).toUpperCase()
                + columnName.substring(1, columnName.length()) + "(" + columnType + " " + columnName + ") {\n"
                + "\t\tthis." + columnName + " = " + columnName + ";\n" + "\t}\n\n";
        return sb;
    }

}
