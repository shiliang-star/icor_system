package com.shiliang.icor.hander;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import com.shiliang.icor.pojo.enums.ManuscriptStatus;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author SL
 * @create 2020-10-24-19:57
 * @description
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    private HttpSession session;

    @Override
    public void insertFill(MetaObject metaObject) {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        this.setFieldValByName("creator", username, metaObject);
        this.setFieldValByName("uploadPerson", username, metaObject);
        this.setFieldValByName("creationTime", new Date(), metaObject);
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
        this.setFieldValByName("modifier", username, metaObject);
        this.setFieldValByName("modifiedTime", new Date(), metaObject);
        //默认为未审核
        this.setFieldValByName("isApproved", 0, metaObject);
        Object object = metaObject.getOriginalObject();
        if (object instanceof ManuscriptEntity) {
            ManuscriptEntity manuscriptEntity = (ManuscriptEntity) object;
            if (manuscriptEntity.getStatus() == null) {
                this.setFieldValByName("status", ManuscriptStatus.Saved.getCode(), metaObject);
            }
        }
        this.setFieldValByName("isDeleted", 0, metaObject);
        this.setFieldValByName("version", 1L, metaObject);
        this.setFieldValByName("operCreateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        this.setFieldValByName("modifier", username, metaObject);
        this.setFieldValByName("modifiedTime", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

}
