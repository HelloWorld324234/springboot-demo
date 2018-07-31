package cn.saytime.framework.core.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

public class DataTableResponse<T> {

    @ApiModelProperty("分页前的总条目数")
    private Long returnTotalItems;
    @ApiModelProperty("分页后的数据")
    private T data;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Long getReturnTotalItems() {
        return returnTotalItems;
    }

    public void setReturnTotalItems(Long returnTotalItems) {
        this.returnTotalItems = returnTotalItems;
    }

    public static DataTableResponse empty() {
        DataTableResponse o = new DataTableResponse();
        o.setReturnTotalItems(Long.valueOf(0L));
        o.setData(new ArrayList());
        return o;
    }
}
