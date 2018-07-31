package cn.saytime.framework.core.pojo.model;

import cn.saytime.framework.core.pojo.ErrorCode;
import cn.saytime.framework.core.pojo.Language;

import java.util.HashMap;

public class ErrorMessage {

    private HashMap<ErrorCode, String> zh = new HashMap();

    public ErrorMessage(HashMap<ErrorCode, String> zh) {
        this.zh = zh;
    }

    public String getInternationalErrorMessage(ErrorCode key, Language language) {
      /*  switch(null.$SwitchMap$com$cloudlinkscm$loms$framework$core$pojo$Language[language.ordinal()]) {
            case 1:
                return (String)this.zh.get(key);
            case 2:
                return (String)this.en.get(key);
            default:
                return (String)this.zh.get(key);
        }*/
        return (String)this.zh.get(key);
    }

    public String getInternationalErrorMessage(ErrorCode key, Language language, Object... args) {
        String msg = this.getInternationalErrorMessage(key, language);
        return String.format(msg, args);
    }

    public String getErrorMessage(ErrorCode key) {
        return this.zh.get(key);
    }

}
