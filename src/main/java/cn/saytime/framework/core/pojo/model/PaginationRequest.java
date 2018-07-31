package cn.saytime.framework.core.pojo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;

public abstract class PaginationRequest {
    private static final int DEF_OFFSET = 0;
    private static final int DEF_LIMIT = 20;
    @ApiParam(
            value = "&rt;={分页区间开始坐标}",
            defaultValue = "0"
    )
    private Integer itemFrom;
    @ApiParam(
            value = "&lt;{分页区间结束坐标}",
            defaultValue = "20"
    )
    private Integer itemTo;

    public PaginationRequest() {
    }

    /** @deprecated */
    public Integer getItemFrom() {
        return this.itemFrom != null && this.itemFrom.intValue() >= 0?this.itemFrom:Integer.valueOf(0);
    }

    /** @deprecated */
    public void setItemFrom(Integer itemFrom) {
        this.itemFrom = itemFrom;
    }

    /** @deprecated */
    public Integer getItemTo() {
        return this.itemTo != null && this.itemTo.intValue() > 0?this.itemTo:Integer.valueOf(this.getItemFrom().intValue() + 20);
    }

    /** @deprecated */
    public void setItemTo(Integer itemTo) {
        this.itemTo = itemTo;
    }

    @JsonIgnore
    public Integer getOffset() {
        return this.getItemFrom();
    }

    @JsonIgnore
    public void setOffset(Integer offset) {
        this.setItemFrom(offset);
    }

    @JsonIgnore
    public Integer getLimit() {
        return Integer.valueOf(this.getItemTo().intValue() - this.getItemFrom().intValue());
    }

    @JsonIgnore
    public void setLimit(Integer limit) {
        this.setItemTo(Integer.valueOf(this.getOffset().intValue() + limit.intValue()));
    }

    @JsonIgnore
    public Integer getPageIndex() {
        return Integer.valueOf(this.getOffset().intValue() / this.getLimit().intValue() + 1);
    }

    @JsonIgnore
    public Integer getPageSize() {
        return this.getLimit();
    }
}

