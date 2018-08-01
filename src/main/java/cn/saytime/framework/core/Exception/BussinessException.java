package cn.saytime.framework.core.Exception;

import cn.saytime.framework.core.pojo.ErrorCode;
import lombok.Data;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 15:12
 * @Description: 自定义Exception
 */
@Data
public class BussinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    private ErrorCode errorCode;

    public BussinessException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BussinessException(String message) {
        super();
        this.message = message;
    }

    public BussinessException(int code, String message) {
        super();
        this.message = message;
        this.code = code;
    }

}
