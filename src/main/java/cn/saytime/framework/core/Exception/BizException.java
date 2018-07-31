package cn.saytime.framework.core.Exception;

import cn.saytime.framework.core.pojo.ErrorCode;

public class BizException extends RuntimeException {

    private ErrorCode errorCode;

    public BizException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

}
