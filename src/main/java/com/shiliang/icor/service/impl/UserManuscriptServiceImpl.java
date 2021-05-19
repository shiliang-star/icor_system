package com.shiliang.icor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiliang.icor.mapper.UserManuscriptMapper;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import com.shiliang.icor.pojo.entity.UserEntity;
import com.shiliang.icor.pojo.entity.UserManuscriptEntity;

import com.shiliang.icor.pojo.vo.ManuscriptSearchForm;
import com.shiliang.icor.pojo.vo.ManuscriptVO;
import com.shiliang.icor.pojo.vo.UserManuscriptVO;
import com.shiliang.icor.service.UserManuscriptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-04-25
 */
@Service
public class UserManuscriptServiceImpl extends ServiceImpl<UserManuscriptMapper, UserManuscriptEntity> implements UserManuscriptService {

    @Override
    public Page<UserManuscriptVO> pageUserManuscriptCondition(Integer currentPage, Integer pageSize, ManuscriptSearchForm manuscriptSearchForm, HttpServletRequest request) {

        Page<UserManuscriptVO> page = new Page<UserManuscriptVO>(currentPage, pageSize);
        QueryWrapper<UserManuscriptVO> queryWrapper = new QueryWrapper<UserManuscriptVO>();

//        String name = manuscriptSearchForm.getName();
//        String code = manuscriptSearchForm.getCode();
//        String status = manuscriptSearchForm.getStatus();
//        Date startTime = manuscriptSearchForm.getStartTime();
//        Date endTime = manuscriptSearchForm.getEndTime();
//        if (!StringUtils.isEmpty(name)) {
//            queryWrapper.like("m.name", name);
//        }
//        if (!StringUtils.isEmpty(code)) {
//            queryWrapper.like("m.code", code);
//        }
//
//        if (!StringUtils.isEmpty(status)) {
//            queryWrapper.eq("m.status", status);
//        }
//        if (startTime != null && endTime != null) {
//            queryWrapper.between("m.creation_time", startTime, endTime);
//        }

        baseMapper.page(page, queryWrapper);
        return page;
    }

    @Override
    public List<UserManuscriptVO> getByManuscriptId(String manuscriptId) {
        Assert.notNull(manuscriptId, "稿件编号不能为空");
        return baseMapper.searchByManuscriptId(manuscriptId);
    }
}
