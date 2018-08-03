package cn.saytime.config;

import cn.saytime.framework.webapp.RestfulApiResponse;
import cn.saytime.model.User1;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Auther: yule
 * @Date: 2018/8/3 0003 11:30
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class ReturnValueAspect {

    @Pointcut("execution(public * cn.saytime.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD : {}", request.getMethod());
        log.info("IP : {}", request.getRemoteAddr());
        log.info("CLASS_METHOD : {} . {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        log.info("ARGS : {}", Arrays.toString(joinPoint.getArgs()));

    }
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        RestfulApiResponse<User1> restfulApiResponse = new RestfulApiResponse<>();
        restfulApiResponse.setResult((User1) ret);
        restfulApiResponse.setReturnMsg("处理成功");
        restfulApiResponse.setErrorCode("100");
        log.info("方法的返回值 : {}" + restfulApiResponse.toString());
    }

}
