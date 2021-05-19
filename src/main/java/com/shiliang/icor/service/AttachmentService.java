package com.shiliang.icor.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.vo.AttachmentSearchForm;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-25
 */
public interface AttachmentService extends IService<AttachmentEntity> {

    /**
     * 上传附件
     * @param file
     * @return
     */
    CommonResult upload(MultipartFile file);

    Page<AttachmentEntity> pageAttachmentCondition(Integer currentPage, Integer pageSize, AttachmentSearchForm attachmentSearchForm);

}
