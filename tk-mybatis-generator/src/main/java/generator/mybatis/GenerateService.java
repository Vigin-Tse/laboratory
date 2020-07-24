/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : GenerateService.java
 * @author : wangyp
 * @date : 2020/4/2 上午8:08
 * @version  1.0
 */

package generator.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GenerateService extends BaseInfo {

    private final String packageName = "com.bpg.bigdataanalysis.ees.service;";
    private final String path = absPath + "\\src\\main\\java\\" + packageName.replace(".", "\\")
            .substring(0, packageName.length() - 1) + "\\";

    public GenerateService() {
        dbMetaData = getDbMetaData();
    }

    /**
     * 生成service接口
     *
     * @return
     */
    public void getService() {
        List<String> tableList = getAllTableList("ees_%");
        for (String tableName : tableList) {
            String table = getFormatString(tableName, true);
            String className = table + "Service";
            String sb = "package " + packageName + "\n\n"
                    + "import com.bpg.bigdataanalysis.base.Service;\n"
                    + "import " + packageNameDto.substring(0, packageNameDto.length() - 1) + "." + table + ";\n\n"
                    + ("/**\n" + " * @author xieweij\n" + " */\n")
                    + "public interface " + className + " extends Service<" + table + ">" + " {\n\n" + "}\n";
            outputToFile(className + ".java", sb, path);
        }
    }
}
