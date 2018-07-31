package cn.saytime.exception;

import cn.saytime.framework.core.Exception.BizExceptionWithArguments;

public class DemoBizException extends BizExceptionWithArguments {
    public DemoBizException(DemoErrorCode errorCode, String... args) {
        super(errorCode, args);
    }

    public DemoBizException(DemoErrorCode demoErrorCode) {
        super(demoErrorCode);
    }
}
