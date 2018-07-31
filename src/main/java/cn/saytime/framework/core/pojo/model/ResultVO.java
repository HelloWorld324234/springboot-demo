package cn.saytime.framework.core.pojo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 15:24
 * @Description:
 */
@Data
public class ResultVO {

    private String msg;

    private int code;

    private StringBuilder result;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

}
