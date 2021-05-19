package com.shiliang.icor.service.impl;



import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.AttachmentMapper;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.vo.AttachmentSearchForm;
import com.shiliang.icor.service.AttachmentService;
import com.shiliang.icor.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-25
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, AttachmentEntity> implements AttachmentService {

    @Override
    public CommonResult upload(MultipartFile file) {
        try {
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = ConstantPropertiesUtils.END_POINT;
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
            String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
            String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取文件名称
            String filename = file.getOriginalFilename();

            //在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");

            filename = uuid + filename;

            //把文件按照日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");

            filename = datePath + "/" + filename;

            // 上传文件流。
            InputStream inputStream = file.getInputStream();

            //第二个参数 上传到oss文件路径和文件名称
            ossClient.putObject(bucketName,filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //把文件路径返回
            String url = "https://" + bucketName + "." + endpoint + "/" + filename;
            return CommonResult.success(url);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<AttachmentEntity> pageAttachmentCondition(Integer currentPage, Integer pageSize, AttachmentSearchForm attachmentSearchForm) {
        return null;
    }
}
