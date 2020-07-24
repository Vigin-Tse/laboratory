package generator.mybatis;

import java.util.List;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/16 10:40
 */
public class GeneratorMain {

    public static void main(){

        // 实体类
        GenerateEntity model = new GenerateEntity();
        List<String> tableList = model.getAllTableList("ees_%");
        for (String tableName : tableList) {
            model.getTableColumns(tableName);
        }

        //mapper
        GenerateMapper mapper = new GenerateMapper();
        mapper.getService();

        //mapper xml
        GenerateMybatisXml mapperXml = new GenerateMybatisXml();
        for (String tableName : tableList) {
            mapperXml.getTableColumns(tableName);
        }

        //service
        GenerateService service = new GenerateService();
        service.getService();

        //serviceimpl
        GenerateServiceImpl serviceImpl = new GenerateServiceImpl();
        serviceImpl.getServiceImpl();
    }
}
