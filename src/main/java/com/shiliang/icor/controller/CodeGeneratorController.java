package com.shiliang.icor.controller;


import com.shiliang.icor.pojo.CommonResult;
import com.shiliang.icor.pojo.enums.OperTypeConst;
import com.shiliang.icor.service.CodeGeneratorService;
import com.shiliang.icor.utils.OperLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ShiLiang
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/code-generator")
public class CodeGeneratorController {


    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("获取流水号")
    @GetMapping("/getCodeSerialByPessimisticLock")
    @OperLog(operModule = "流水号编码模块", operType = OperTypeConst.ADD, operDesc = "获取流水号(悲观锁)")
    public CommonResult<String> getCodeSerialByPessimisticLock(@RequestParam("type") String type) {
//        ReentrantLock lock = new ReentrantLock();
//        //加锁
//        lock.lock();
////        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent("lock", type);
////        if (!flag) {
//            return CommonResult.failed("系统繁忙");
//        }
        try {
            String serialCode = codeGeneratorService.getCodeSerial(type);
            return CommonResult.success(serialCode);
        } finally {
//            stringRedisTemplate.delete("lock");
//            释放锁
//            lock.unlock();
        }
    }



    @ApiOperation("获取流水号")
    @GetMapping("/getCodeSerialByOptimisticLock")
    @OperLog(operModule = "流水号编码模块", operType = OperTypeConst.ADD, operDesc = "获取流水号(乐观锁）")
    public CommonResult<String> getCodeSerialByOptimisticLock(@RequestParam("type") String type) {
            String serialCode = codeGeneratorService.getCodeSerialByOptimisticLock(type);
            return CommonResult.success(serialCode);
    }
}

