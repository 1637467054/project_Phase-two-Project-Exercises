package com.course.common.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import com.course.common.util.UuidUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;


/**
 * @version :V1.0
 * @auto :李俨稹
 * @date ：Created in 2020/8/11  20:23
 * @description :这个是日志的切面
 * @modified By :
 */
@Aspect//AOP作用是把当前类表示位一个切面供容器读取
@Component//依赖注入的方法，就好行@Server一样，只不过@Server是用在服务层的
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**定义一个切点*/
    @Pointcut("execution(public * com.course.*.controller..*Controller.*(..))")
    public void controllerPointcut(){}

    /**
     * 前置通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 日志编号(这个UUID的字符串是自定义的)
        MDC.put("UUID", UuidUtil.getShortUuid());

        // 开始打印请求日志
        //RequestContextHolder中的ServletRequestAttributes来存放「用于存放用户信息的session」。
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //客户端浏览器发出的请求被封装成为一个HttpServletRequest对象。对象包含了客户端请求信息包括请求的地址，
        // 请求的参数，提交的数据，上传的文件客户端的ip甚至客户端操作系统都包含在其内。
        HttpServletRequest request = attributes.getRequest();
        //获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
        Signature signature = joinPoint.getSignature();
        //获取方法名
        String name = signature.getName();

        // 打印业务操作
        String nameCn = "";
        //字符串1.ontains("字符串2")当字符串1中包含字符串2时返回true
        if (name.contains("list") || name.contains("query")) {
            nameCn = "查询";
        } else if (name.contains("save")) {
            nameCn = "保存";
        } else if (name.contains("delete")) {
            nameCn = "删除";
        } else {
            nameCn = "操作";
        }

        // 使用反射，获取业务名称
        Class clazz = signature.getDeclaringType();
        Field field;
        String businessName = "";
        try {
            field = clazz.getField("BUSINESS_NAME");
            if (!StringUtils.isEmpty(field)) {
                businessName = (String) field.get(clazz);
            }
        } catch (NoSuchFieldException e) {
            LOG.error("未获取到业务名称");
        } catch (SecurityException e) {
            LOG.error("获取业务名称失败", e);
        }

        // 打印请求信息
        LOG.info("------------- 【{}】{}开始 -------------", businessName, nameCn);
        LOG.info("请求地址: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("类名方法: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("远程地址: {}", request.getRemoteAddr());

        // 打印请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest
                    || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("请求参数: {}", JSONObject.toJSONString(arguments, excludefilter)); // 为空的会不打印，但是像图片等长字段也会打印
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //执行目标方法
        Object result = proceedingJoinPoint.proceed();
        // 排除字段，敏感字段或太长的字段不显示
        String[] excludeProperties = {"password", "shard"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("返回结果: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }
}
