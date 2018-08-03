package cn.saytime.framework.core.pojo.model;

import lombok.Data;

/**
 * @Auther: yule
 * @Date: 2018/7/31 0031 15:24
 * @Description:
 */
@Data
public class ResultVO {

    private String msg;

    private String code;

    private StringBuilder result;

    private String time;

}
