package com.shiliang.icor.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiliang.icor.mapper.CodeGeneratorMapper;
import com.shiliang.icor.pojo.entity.CodeGeneratorEntity;
import com.shiliang.icor.pojo.enums.BusinessTypeEnum;
import com.shiliang.icor.service.CodeGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-28
 */
@Service
@Slf4j
public class CodeGeneratorServiceImpl extends ServiceImpl<CodeGeneratorMapper, CodeGeneratorEntity> implements CodeGeneratorService {


    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    private final String firstSerial = "0001";

    private final String MIDDLELINE = "-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getCodeSerial(String type) {
        try {
            String codeSerial;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            CodeGeneratorEntity codeGeneratorInsertEntity = new CodeGeneratorEntity();
            String oldSerialCode = baseMapper.findLatestSerialCode(type, new Date());
            String rule = "常量+日期+流水号";
            String prefix = BusinessTypeEnum.getBusinessTypePrefix(type) + MIDDLELINE;
            String newDateString = formatter.format(new Date());
            if (oldSerialCode == null) {
                //第一次生成编码
                codeSerial = prefix + newDateString + firstSerial;
            } else {
                String oldDateString = oldSerialCode.substring(3, 11);
                if (newDateString.equals(oldDateString)) {
                    DecimalFormat df = new DecimalFormat("0000");
                    String serial = df.format(1 + Integer.parseInt(oldSerialCode.substring(11, 15)));
                    codeSerial = prefix + oldDateString + serial;
                } else {
                    codeSerial = prefix + newDateString + firstSerial;
                }
            }
            codeGeneratorInsertEntity.setSerialCode(codeSerial);
            codeGeneratorInsertEntity.setType(type);
            codeGeneratorInsertEntity.setRule(rule);
            baseMapper.insert(codeGeneratorInsertEntity);
            return codeSerial;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }





    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getCodeSerialByOptimisticLock(String type) {
        try {
            String codeSerial;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            QueryWrapper<CodeGeneratorEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", type);
            CodeGeneratorEntity oldCodeGeneratorEntity = baseMapper.selectOne(queryWrapper);
            CodeGeneratorEntity codeGeneratorInsertEntity = new CodeGeneratorEntity();
            String rule = "常量+日期+流水号";
            String prefix = BusinessTypeEnum.getBusinessTypePrefix(type) + MIDDLELINE;
            String newDateString = formatter.format(new Date());
            if (oldCodeGeneratorEntity == null) {
                //第一次生成编码
                codeGeneratorInsertEntity.setType(type);
                codeGeneratorInsertEntity.setRule(rule);
                codeSerial = prefix + newDateString + firstSerial;
                codeGeneratorInsertEntity.setSerialCode(codeSerial);
                baseMapper.insert(codeGeneratorInsertEntity);
            } else {
                String oldSerialCode = oldCodeGeneratorEntity.getSerialCode();
                String oldDateString = oldSerialCode.substring(3, 11);
                if (newDateString.equals(oldDateString)) {
                    DecimalFormat df = new DecimalFormat("0000");
                    String serial = df.format(1 + Integer.parseInt(oldSerialCode.substring(11, 15)));
                    codeSerial = prefix + oldDateString + serial;
                } else {
                    codeSerial = prefix + newDateString + firstSerial;
                }
                oldCodeGeneratorEntity.setSerialCode(codeSerial);
                baseMapper.updateById(oldCodeGeneratorEntity);
            }
            return codeSerial;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
