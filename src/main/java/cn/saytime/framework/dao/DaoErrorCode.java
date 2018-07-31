package cn.saytime.framework.dao;

import cn.saytime.framework.core.pojo.ErrorCode;
import cn.saytime.framework.core.pojo.model.ErrorMessage;
import com.sun.tools.internal.xjc.Language;

import java.util.HashMap;

public enum  DaoErrorCode implements ErrorCode {

    INSERT_EXCEPTION("501"),
    UPDATE_EXCEPTION("502"),
    DELETE_EXCEPTION("503"),
    SELECT_EXCEPTION("504");

    private String code;
    private static HashMap<ErrorCode, String> zh = new HashMap<ErrorCode, String>();
    private static ErrorMessage errorMessage;

    private DaoErrorCode(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
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

        zh.put(INSERT_EXCEPTION, "插入数据失败");
        zh.put(DELETE_EXCEPTION, "删除数据失败");
        zh.put(SELECT_EXCEPTION, "获取数据失败");
        zh.put(UPDATE_EXCEPTION, "更新数据失败");

        errorMessage = new ErrorMessage(zh);

    }

    @Override
    public String getInternationalMessage(Language var1, Object... var2) {
        return errorMessage.getInternationalErrorMessage(this, var1, var2);
    }
}
