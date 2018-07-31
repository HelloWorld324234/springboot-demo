package cn.saytime.model;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 11:21
 * @Description:
 */
public class ResultInfo {

    public long code;
    public String message;
    public Object data;

    public long getCode() {
        if(code==0l){
            code=200l;
        }
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        if(message==null){
            message="处理成功！";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
