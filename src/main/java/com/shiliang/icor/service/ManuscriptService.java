package com.shiliang.icor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.UserManuscriptEntity;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserApproveVO;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-23
 */
public interface ManuscriptService extends IService<ManuscriptEntity> {

    /**
     *   分页条件查询稿件信息
     * @param manuscriptSearchForm
     * @return
     */
    Page<ManuscriptVO> pageManuscriptCondition(Integer currentPage, Integer pageSize, ManuscriptSearchForm manuscriptSearchForm, HttpServletRequest request);

    /**
     * 保存稿件
     * @param manuscriptEntity
     * @return
     */
    Boolean saveManuscript(ManuscriptEntity manuscriptEntity,String attachmentId);


    /**
     * 更新稿件
     * @param manuscriptEntity
     * @return
     */
    Boolean updateManuscript(ManuscriptEntity manuscriptEntity);

    /**
     * 提交稿件
     * @param ids
     * @return
     */
    List<ManuscriptEntity> submitManuscript(String[] ids);

    /**
     * 提交稿件
     * @param manuscriptEntity
     * @return
     */
    Boolean submitManuscript(ManuscriptEntity manuscriptEntity,String attachmentId);

    /**
     * 回收稿件
     * @param ids
     * @return
     */
    List<ManuscriptEntity> cancelSubmitManuscript(String[] ids);

    /**
     * 审核稿件
     * @param  userApproveVO
     * @param userApproveVO
     * @return
     */
    UserManuscriptEntity approveManuscript(HttpServletRequest request,UserApproveVO userApproveVO);

    List<ManuscriptEntity> cancelApproveManuscript(String[] ids);

    List<ManuscriptEntity> rejectManuscript(String[] ids, String rejectReason);

    void saveUserManuscriptRealtionShip(String manuscriptId, String[] userIds);

    void exportExcel(HttpServletRequest request, HttpServletResponse response, ManuscriptSearchForm manuscriptSearchForm);

    void importManuscriptExcel(MultipartFile file) throws IOException;

    String uploadOSS(MultipartFile file);

}
