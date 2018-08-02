package cn.saytime.model.others;

/**
 * User1: yule
 * Date: 2018/4/13 14:54
 * Description:
 */
public class JsonResult {

    private String status = null;

    private Object result = null;

    public JsonResult() {
    }

    public JsonResult(String status, Object result) {
        this.status = status;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public JsonResult status(String status) {
        this.status = status;
        return this;
    }
}