package com.shiliang.icor.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.ConferenceEntity;
import com.shiliang.icor.pojo.vo.ConferenceSearchForm;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-03-22
 */
public interface ConferenceService extends IService<ConferenceEntity> {

    Boolean saveConference(ConferenceEntity conferenceEntity);

    Boolean updateManuscript(ConferenceEntity conferenceEntity);

    Page<ConferenceEntity> pageConferenceCondition(Integer currentPage, Integer pageSize, ConferenceSearchForm conferenceSearchForm);

    void exportExcel(HttpServletResponse response) throws IOException;

    void importConferenceExcel(MultipartFile file) throws IOException;
}
