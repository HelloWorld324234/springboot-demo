package cn.saytime.framework.core.Exception;

import cn.saytime.framework.core.pojo.ErrorCode;
import cn.saytime.framework.core.pojo.model.ErrorMessage;
import com.sun.tools.internal.xjc.Language;

import java.util.HashMap;

public enum GenericErrorCode implements ErrorCode {

    INVALID_ARGUMENT_EXCEPTION("101");

    private static HashMap<ErrorCode, String> zh = new HashMap();
    private static ErrorMessage errorMessage;
    private String code;


    private GenericErrorCode(String code) {
        this.code = code;
    }


    public String getInternationalMessage(Language language) {
        return errorMessage.getInternationalErrorMessage(this, language);
    }

    public String getInternationalMessage(Language language, Object... args) {
        return errorMessage.getInternationalErrorMessage(this, language, args);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return errorMessage.getErrorMessage(this);
    }

    static {
        zh.put(INVALID_ARGUMENT_EXCEPTION, "无效参数：%s");
        errorMessage = new ErrorMessage(zh);
    }
}
