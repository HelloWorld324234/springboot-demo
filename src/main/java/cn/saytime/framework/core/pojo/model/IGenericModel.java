package cn.saytime.framework.core.pojo.model;

import java.util.Date;

/**
 * 通用实体类模型
 *
 * @param <PK> 主键类型，一般为String
 */
public interface IGenericModel<PK> {

    PK getId();

    void setId(PK var1);

    String getInsertUser();

    void setInsertUser(String var1);

    Date getInsertTime();

    void setInsertTime(Date var1);

    String getUpdateUser();

    void setUpdateUser(String var1);

    Date getUpdateTime();

    void setUpdateTime(Date var1);

    /**
     *初始化字段
     */
    void init();

}
