package cn.saytime.config;

import cn.saytime.constant.SupportsConstant;
import cn.saytime.model.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Auther: yule
 * @Date: 2018/8/3 0003 11:50
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //获取当前处理请求的controller的方法
        String methodName=methodParameter.getMethod().getName();
        log.info("获取当前处理请求的controller的方法-->{}", methodName);
        // 不拦截/不需要处理返回值 的方法
        String method= "loginCheck"; //如登录
        //不拦截与swagger有关的方法
        if (methodName.contains(SupportsConstant.uiConfiguration) || methodName.contains(SupportsConstant.getDocumentation) || methodName.contains(SupportsConstant.swaggerResources)) {
            return false;
        }
        return !method.equals(methodName);
    }

    @Override
    public Object beforeBodyWrite(Object returnValue,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(new Long(100));
        resultInfo.setMessage("处理成功");
        resultInfo.setData(returnValue);
        return resultInfo;
    }
}
