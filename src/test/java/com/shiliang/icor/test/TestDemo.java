package com.shiliang.icor.test;

import com.shiliang.icor.IcorSystemApplication;
import com.shiliang.icor.controller.ManuscriptController;
import com.shiliang.icor.mapper.ManuscriptMapper;
import com.shiliang.icor.pojo.entity.ManuscriptEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author sl
 * @Date 2021/2/25 11:48
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={IcorSystemApplication.class})
public class TestDemo {

    @Autowired
    private ManuscriptMapper manuscriptMapper;

    @Autowired
    private ManuscriptController manuscriptController;


    @Test
    public void test1() {
        ManuscriptEntity manuscriptEntity = manuscriptMapper.selectById("1364778971458134018");
        manuscriptEntity.setName("时亮");
        manuscriptMapper.updateById(manuscriptEntity);
    }


    @Test
    public void test2() {
        ManuscriptEntity manuscriptEntity = new ManuscriptEntity();
        manuscriptEntity.setId("1364778971458134018");
        manuscriptEntity.setName("时亮测试的");
//        manuscriptEntity.setCode("202020202");
//        manuscriptController.updateManuscript(manuscriptEntity);
    }
}
