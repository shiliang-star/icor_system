package com.shiliang.icor.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.AttachmentEntity;
import com.shiliang.icor.pojo.vo.AttachmentBusinessObjectSearch;
import com.shiliang.icor.pojo.vo.AttachmentSearchForm;
import com.shiliang.icor.pojo.vo.AttachmentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    String upload(String businessType,MultipartFile file);

    Page<AttachmentEntity> pageAttachmentCondition(Integer currentPage, Integer pageSize, AttachmentSearchForm attachmentSearchForm);

    List<AttachmentVO> getAttachmentByBusinessObject(AttachmentBusinessObjectSearch attachmentBusinessObjectSearch);

}
