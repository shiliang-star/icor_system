package com.shiliang.icor.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author sl
 * @version 1.0.0
 * @ClassName OSSUtil.java
 * @Description 阿里云OSS工具类
 * @createTime 2021年05月16日 08:44:00
 */
public class OSSUtil {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private final static String ENDPOINT = ConstantPropertiesUtils.END_POINT;
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
    private final static String ACCESS_KEY_ID = ConstantPropertiesUtils.ACCESS_KEY_ID;
    private final static String ACCESS_KEY_SECRET = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
    private final static String BUCKET_NAME = ConstantPropertiesUtils.BUCKET_NAME;

    public static String upload(MultipartFile file) {
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

            //获取文件名称
            String filename = file.getOriginalFilename();

            //在文件名称里面添加随机唯一的值
            String uuid = java.util.UUID.randomUUID().toString().replaceAll("-", "");

            filename = uuid + filename;

            //把文件按照日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");

            filename = datePath + "/" + filename;

            // 上传文件流。
            InputStream inputStream = file.getInputStream();

            //第二个参数 上传到oss文件路径和文件名称
            ossClient.putObject(BUCKET_NAME, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把文件路径返回
            String url = "https://" + BUCKET_NAME + "." + ENDPOINT + "/" + filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void download(String objectName) throws IOException {
        BufferedReader reader = null;
        OSS ossClient= null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(BUCKET_NAME, objectName);

            // 读取文件内容。
            System.out.println("Object content:");
             reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                System.out.println("\n" + line);
            }

        } catch (OSSException | IOException | ClientException e) {
            e.printStackTrace();
        } finally {
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            reader.close();
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}
