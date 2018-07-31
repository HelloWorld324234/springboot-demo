package cn.saytime.framework.core.pojo;

import com.sun.tools.internal.xjc.Language;

public interface AppCode {

    /**
     * 获取异常编码
     *
     * @return
     */
    String getCode();

    /**
     * 获取异常详细信息
     *
     * @return
     */
    String getMessage();

    /**
     * 获取语言种类
     *
     * @param var1
     * @return
     */
    String getInternationalMessage(Language var1);

}
