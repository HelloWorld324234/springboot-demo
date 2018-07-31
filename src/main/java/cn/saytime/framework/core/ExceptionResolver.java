package cn.saytime.framework.core;

import cn.saytime.framework.core.Exception.BussinessException;
import cn.saytime.framework.core.pojo.model.ResultVO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 15:16
 * @Description: 自定义统一异常处理类
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger("exceptionLog");


    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse response,
                                         Object o, Exception e) {
        ResultVO resultVO = new ResultVO();
        StringBuilder stringBuffer = new StringBuilder();
        // 处理异常
        if (e instanceof BussinessException) {
            resolverBussinessException(e, stringBuffer, resultVO);
        }
        // 处理参数异常
        else if (e instanceof BindException) {
            resolverBindException(e, stringBuffer, resultVO);
        }
        // 处理其它异常
        else {
            resolverOtherException(e, stringBuffer, resultVO);
        }

        resultVO.setCode(0);
        resultVO.setResult(stringBuffer);
        resultVO.setTime(new Date());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        try {
            response.getWriter().write(JSON.toJSONString(resultVO));
        } catch (IOException ex) {
            logger.error("与客户端通讯异常：" + e.getMessage(), e);
            ex.printStackTrace();
        }

        logger.debug("异常：" + e.getMessage(), e);
        e.printStackTrace();

        return new ModelAndView();

    }

    /**
     * 处理业务层异常
     *
     * @param ex
     * @param sb
     * @param resultVO
     */
    private void resolverBussinessException(Exception ex, StringBuilder sb, ResultVO resultVO) {
        BussinessException bussinessException = (BussinessException) ex;
        sb.append(bussinessException.getMessage());
        addResult(resultVO, "业务异常");
    }

    /**
     * 处理参数绑定异常
     *
     * @param ex
     * @param stringBuilder
     * @param resultVO
     */
    private void resolverBindException(Exception ex, StringBuilder stringBuilder, ResultVO resultVO) {
        BindException bindException = (BindException) ex;
        List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append(fieldError.getObjectName());
            stringBuilder.append("对象的");
            stringBuilder.append(fieldError.getField());
            stringBuilder.append("字段");
            stringBuilder.append(fieldError.getDefaultMessage());
        }
        addResult(resultVO, "参数传递异常:" + stringBuilder);
    }

    /**
     * 处理其他异常
     *
     * @param ex
     * @param stringBuilder
     * @param resultVO
     */
    private void resolverOtherException(Exception ex, StringBuilder stringBuilder, ResultVO resultVO) {
        stringBuilder.append(ex.getMessage());
        addResult(resultVO, "other exception");
    }


    /**
     * 封装、处理msg
     *
     * @param result
     * @param msg
     */
    private void addResult(ResultVO result, String msg) {
        result.setMsg(msg);
    }
}











