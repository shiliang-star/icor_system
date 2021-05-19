package com.shiliang.icor.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.shiliang.icor.pojo.entity.CodeGeneratorEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-28
 */
public interface CodeGeneratorService extends IService<CodeGeneratorEntity> {

    String getCodeSerial(String type);

    String getCodeSerialByOptimisticLock(String type);
}
