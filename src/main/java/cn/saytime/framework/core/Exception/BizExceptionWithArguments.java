package cn.saytime.framework.core.Exception;

import cn.saytime.framework.core.pojo.ErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BizExceptionWithArguments extends BizException {

    /**
     * 异常参数信息
     */
    private List<String> args = new ArrayList<>();

    public List<String> getArgs() {
        return this.args;
    }

    public BizExceptionWithArguments(ErrorCode errorCode, String... args) {
        super(errorCode);
        if (args.length > 0) {
            this.args = Arrays.asList(args);
        }
    }
}
