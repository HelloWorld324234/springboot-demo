package cn.saytime.framework.core.pojo;

import cn.saytime.framework.core.pojo.model.IGenericModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.Date;

/**
 * 对{@link IGenericModel}中定义的字段实现getting、seting、init方法
 *
 * @param <PK>
 */
@Entity
public abstract class GenericModel<PK> implements IGenericModel<PK>, Serializable {

    @Id
    private PK id;
    private String insertUser;
    @OrderBy("desc")
    private Date insertTime;
    private String updateUser;
    private Date updateTime;

    public GenericModel() {
    }

    public PK getId() {
        return this.id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public String getInsertUser() {
        return this.insertUser;
    }

    public void setInsertUser(String insertUser) {
        this.insertUser = insertUser;
    }

    public Date getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public final void init() {
        Date currentDate = new Date();
        this.setId(this.generateId());
        this.setInsertUser("insertUserTest");
        this.setInsertTime(currentDate);
        this.setUpdateUser((String)null);
        this.setUpdateTime((Date)null);
        this.setDefault();
    }

    protected void setDefault() {
    }

    /**
     * 自动生成主键的方法
     *
     * @return
     */
    protected abstract PK generateId();

}
