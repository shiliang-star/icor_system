package com.shiliang.icor.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.shiliang.icor.exception.ApiException;
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
public class OSSUploadUtil {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private final static String ENDPOINT = ConstantPropertiesUtils.END_POINT;
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
    private final static String ACCESS_KEY_ID = ConstantPropertiesUtils.ACCESS_KEY_ID;
    private final static String ACCESS_KEY_SECRET = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
    private final static String BUCKET_NAME = ConstantPropertiesUtils.BUCKET_NAME;

    /**
     * 上传文件到OSS
     * @param file
     * @return 数组{文件URL，文件名}
     */
    public static String[] upload(MultipartFile file) {
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
            return new String[]{url, filename};
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除OSS单个文件
     * @param fileName
     */
    public static void deleteFile(String fileName) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(BUCKET_NAME, fileName);
        } catch (Exception e){
            throw new ApiException("阿里云OSS删除失败");
        }finally {
            // 关闭OSSClient。
            assert ossClient != null;
            ossClient.shutdown();

        }

    }
}
