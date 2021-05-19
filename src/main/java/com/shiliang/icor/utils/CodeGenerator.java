package com.shiliang.icor.utils;


import com.shiliang.icor.pojo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author sl
 * @Date 2021/2/27 0:42
 * @Description 编码生成器
 */
public class CodeGenerator {

    public final static String MANUSCRIPT = "GJ";
    
    private static final String SERIAL_NUMBER = "XXXX"; // 流水号格式

    private static volatile CodeGenerator codeGenerator ;

    public CodeGenerator(){}

//    @Autowired
//    private static RestTemplate restTemplate;

    private final static String URL = "http://ICOR-CODE-GENERATOR-SERVICE/getCodeSerialByPessimisticLock";


    /**
     * 取得CodeGenerator的单例实现(DCL双检索机制）
     * @return
     */
    public static CodeGenerator getInstance() {
        if (codeGenerator == null) {
            synchronized (CodeGenerator.class) {
                if (codeGenerator == null) {
                    codeGenerator = new CodeGenerator();
                }
            }
        }
        return codeGenerator;
    }




    public static String generateCode(String type) {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(URL + "type=" + type, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return (String) responseEntity.getBody().getData();
        } else {
            return null;
        }
    }

    public static String getDateString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String format = sdf.format(date);
        return format;
    }

    public static void main(String[] args) {

        System.out.println(CodeGenerator.getInstance().generaterNextNumber("202102270001"));

    }

    /**
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber(String sno) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (sno == null) {
            id = formatter.format(date) ;
        } else {
//            int count = SERIAL_NUMBER.length();
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < count; i++) {
//                sb.append("0");
//            }
            DecimalFormat df = new DecimalFormat("0000");
            id = formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(8, 12)));
        }
        return id;
    }
}
