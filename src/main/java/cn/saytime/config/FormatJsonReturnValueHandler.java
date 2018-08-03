package cn.saytime.config;

import cn.saytime.annotation.AppResponsBody;
import cn.saytime.framework.webapp.RestfulApiResponse;
import cn.saytime.model.ResultInfo;
import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 11:17
 * @Description:
 */
public class FormatJsonReturnValueHandler implements HandlerMethodReturnValueHandler {

    /**
     *该处理程序是否支持给定的方法返回类型(只有返回true才回去调用handleReturnValue)
     *
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        System.err.println("FormatJsonReturnValueHandler---->:" + returnType.getMethodAnnotation(AppResponsBody.class));
        return returnType.getMethodAnnotation(AppResponsBody.class) != null;
    }

    /**
     * 处理给定的返回值
     * 通过向 ModelAndViewContainer 添加属性和设置视图或者
     * 通过调用 ModelAndViewContainer.setRequestHandled(true) 来标识响应已经被直接处理(不再调用视图解析器)
     *
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter writer = null;
        Gson gson = new Gson();
        ResultInfo info = new ResultInfo();
        RestfulApiResponse restfulApiResponse = new RestfulApiResponse();
        restfulApiResponse.setResult(returnValue);
        info.setData(returnValue);
        try {
            writer = response.getWriter();
            writer.write(gson.toJson(info));
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
