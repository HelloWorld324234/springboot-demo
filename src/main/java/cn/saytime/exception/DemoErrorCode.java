package cn.saytime.exception;

import cn.saytime.framework.core.pojo.ErrorCode;
import cn.saytime.framework.core.pojo.Language;
import cn.saytime.framework.core.pojo.model.ErrorMessage;

import java.util.HashMap;

/**
 * 区间 100-200  从100开始
 */
public enum DemoErrorCode implements ErrorCode {
    USER_NAME_REPEAT_EXCEPTION("100");

    private static HashMap<ErrorCode,String> zh = new HashMap<>();
    private String code;
    private static ErrorMessage errorMessage = new ErrorMessage(zh);

    DemoErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return errorMessage.getErrorMessage(this);
    }

    @Override
    public String getInternationalMessage(Language var1) {
        return errorMessage.getInternationalErrorMessage(this, var1);
    }

    static {

        zh.put(DemoErrorCode.USER_NAME_REPEAT_EXCEPTION,"用户名已经存在");

    }

    @Override
    public String getInternationalMessage(Language var1, Object... var2) {
        return errorMessage.getInternationalErrorMessage(this, var1, var2);
    }
}
