package cn.saytime.framework.dao.Exception;

import cn.saytime.framework.core.Exception.BizExceptionWithArguments;
import cn.saytime.framework.dao.DaoErrorCode;

public class DaoBizException extends BizExceptionWithArguments {

    public DaoBizException(DaoErrorCode daoErrorCode, String... args) {
        super(daoErrorCode, args);
    }

    public DaoBizException(DaoErrorCode daoErrorCode) {
        super(daoErrorCode);
    }
}
