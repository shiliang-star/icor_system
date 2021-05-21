package com.shiliang.icor.aspect;

import com.alibaba.fastjson.JSON;


import com.shiliang.icor.pojo.entity.ExceptionLogEntity;
import com.shiliang.icor.pojo.entity.OperationLogEntity;
import com.shiliang.icor.service.ExceptionLogService;
import com.shiliang.icor.service.OperationLogService;
import com.shiliang.icor.utils.IPUtils;
import com.shiliang.icor.utils.OperLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author sl
 * @Date 2021/3/5 14:55
 * @Description 切面处理类，操作日志异常日志记录处理
 */
@Component
@Aspect
@Order(1)
public class OperLogAspect {

    /**
     * 操作版本号
     */
    @Value("${version}")
    private String operVersion;

    @Autowired
    private ExceptionLogService exceptionLogService;

    @Autowired
    private OperationLogService operationLogService;

    private final static ThreadLocal threadLocal = new ThreadLocal();

    /**
     * 设置操作日志切入点，记录操作日志，在注解的位置切入代码
     */
    @Pointcut("@annotation(com.shiliang.icor.utils.OperLog)")
    public void operLogPointCut() {

    }

    @Pointcut("execution(* com.shiliang.icor.controller..*.*(..))")
    public void operExceptionLogPointCut() {

    }



    /**
     * 正常返回通知，拦截用户操作日志，连接点正常执行完成后执行，如果连接点抛出异常，则不会执行
     * @param joinPoint 切入点
     * @param keys 返回结果
     */
    @AfterReturning(value = "operLogPointCut()", returning = "keys")
    public void saveOperLog(JoinPoint joinPoint, Object keys) {
       //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取到的requestAttributes获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        OperationLogEntity operationLogEntity = new OperationLogEntity();
        try {
            //主键ID
//            operationLogEntity.setOperId(UUIDGenerator.generateId());
            //从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = methodSignature.getMethod();
            //获取操作
            OperLog operLog = method.getAnnotation(OperLog.class);
            if (operLog != null) {
                String operModule = operLog.operModule();
                String operType = operLog.operType();
                String operDesc = operLog.operDesc();
                operationLogEntity.setOperModul(operModule);
                operationLogEntity.setOperType(operType);
                operationLogEntity.setOperDesc(operDesc);
            }
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            //请求方法
            operationLogEntity.setOperMethod(methodName);
            //请求的参数
            Object[] args = joinPoint.getArgs();
            Object[] newArgs= args;
            //特殊处理
            if ("分页条件查询稿件信息".equals(operationLogEntity.getOperDesc())) {
                newArgs = new Object[args.length - 1];
                for (int i = 0; i < args.length-1; i++) {
                    newArgs[i] = args[i];
                }
            }
            if (operationLogEntity.getOperDesc().contains("导出")) {
                newArgs = new Object[]{"导出"};
            }

            String params = JSON.toJSONString(newArgs);
//            Map<String, String> paramMap = convertMap(request.getParameterMap());
            //将参数所在的数组转换成json
//            String params = JSON.toJSONString(paramMap);
            //请求参数
            operationLogEntity.setOperRequParam(params);
            //返回结果
            operationLogEntity.setOperRespParam(JSON.toJSONString(keys));
           //获取当前登录用户用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            operationLogEntity.setOperUserName(username);
            //请求IP
            operationLogEntity.setOperIp(IPUtils.getIpAddr(request));
            //请求URI
            operationLogEntity.setOperUri(request.getRequestURI());
            //创建时间
            operationLogEntity.setOperCreateTime(new Date());
            //操作版本
            operationLogEntity.setOperVersion(Long.toString(System.currentTimeMillis()));
            operationLogService.save(operationLogEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 异常返回通知，用于拦截异常日志信息，连接点抛出异常后执行
     * @param joinPoint 连接点
     * @param e  异常信息
     */
    @AfterThrowing(pointcut = "operExceptionLogPointCut()",throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
          // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes
                  .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        ExceptionLogEntity exceptionLogEntity = new ExceptionLogEntity();
        try {
            //从切面织入点处通过反射机制获取织入点的方法
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = methodSignature.getMethod();
            //获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            //获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;
            //请求的参数
//            Map<String, String> paramMap = convertMap(request.getParameterMap());
            //请求的参数
            Object[] args = joinPoint.getArgs();
            String params = JSON.toJSONString(args);
            //将参数所在的数组转换成json
//            String params = JSON.toJSONString(paramMap);
            //请求参数
            exceptionLogEntity.setExcRequParam(params);
            //请求方法名
            exceptionLogEntity.setOperMethod(methodName);
            //异常名称
            exceptionLogEntity.setExcName(e.getClass().getName());
            //异常信息
            exceptionLogEntity.setExcMessage(this.stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));
            //操作员ID
            exceptionLogEntity.setOperUserId("11111");
            //操作员名称
            //获取当前登录用户用户名
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            exceptionLogEntity.setOperUserName(username);
            //请求IP
            exceptionLogEntity.setOperIp(IPUtils.getIpAddr(request));
            //请求URI
            exceptionLogEntity.setOperUri(request.getRequestURI());
            exceptionLogEntity.setOperVersion(Long.toString(System.currentTimeMillis()));

            exceptionLogEntity.setOperCreateTime(new Date());
            exceptionLogService.save(exceptionLogEntity);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 转换request 参数
     * @param paramMap request获取的参数数组
     * @return
     */
    public Map<String, String> convertMap(Map<String, String[]> paramMap) {
        Map<String, String> resultMap = new HashMap<>();
        for (String key : resultMap.keySet()) {
            resultMap.put(key, paramMap.get(key)[0]);
        }
        return resultMap;
    }


    public String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer sb = new StringBuffer();
        for (StackTraceElement element : elements) {
            sb.append(element + "\n");
        }
        String message = exceptionName + ":" + exceptionMessage + "\n\t" + sb.toString();
        return message;
    }





}
