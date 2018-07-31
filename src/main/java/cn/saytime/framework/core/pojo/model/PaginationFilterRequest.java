package cn.saytime.framework.core.pojo.model;


public class PaginationFilterRequest<T> extends PaginationRequest {
    private T filter;

    public PaginationFilterRequest() {
    }

    public T getFilter() {
        return this.filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }
}