/*
 * Copyright (c) 2020-2021 白云电气 All Rights Reserved.
 * FileName : GenerateServiceImpl.java
 * @author : wangyp
 * @date : 2020/4/2 上午10:02
 * @version  1.0
 */

package generator.mybatis;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class GenerateServiceImpl extends BaseInfo {

    private final String packageName = "com.bpg.bigdataanalysis.ees.service.impl;";
    private final String path = absPath + "\\src\\main\\java\\" + packageName.replace(".", "\\")
            .substring(0, packageName.length() - 1) + "\\";

    public GenerateServiceImpl() {
        dbMetaData = getDbMetaData();
    }

    /**
     * 生成impl实现类
     *
     * @return
     */
    public void getServiceImpl() {
        List<String> tableList = getAllTableList("ees_%");
        for (String tableName : tableList) {
            String table = getFormatString(tableName, true);
            String mybatis = table + "Mapper";
            String service = table + "Service";
            String className = service + "Impl";
            String sb = "package " + packageName + "\n\n"
                    + "import com.bpg.bigdataanalysis.base.BaseService;\n"
                    + "import " + packageNameDto.substring(0, packageNameDto.length() - 1) + "." + table + ";\n"
                    + "import " + packageNameService.substring(0, packageNameService.length() - 1) + "." + service
                    + ";\n" + "import org.springframework.stereotype.Service;\n\n"
                    + ("/**\n" + " * @author xieweij\n" + " */\n") + "@Service\n" + "public class "
                    + className + " extends BaseService<" + table + "> implements " + service + " {\n\n" + "}\n";
            outputToFile(className + ".java", sb, path);
        }
    }

}
