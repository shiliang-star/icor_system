package com.shiliang.icor.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.pojo.entity.UserManuscriptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserManuscriptVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-25
 */
public interface UserManuscriptService extends IService<UserManuscriptEntity> {

    Page<UserManuscriptVO> pageUserManuscriptCondition(Integer currentPage, Integer pageSize, ManuscriptSearchForm manuscriptSearchForm, HttpServletRequest request);

    List<UserManuscriptVO> getByManuscriptId(String manuscriptId);

    void exportExcel(HttpServletResponse response);
}
