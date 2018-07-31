package cn.saytime.model.dto;

import lombok.Data;

import javax.persistence.OrderBy;
import java.util.Date;

@Data
public class UserResponse {

    private String id;

    private String name;

    private String address;

    private String insertUser;

    @OrderBy("desc")
    private Date insertTime;

    private String updateUser;

    private Date updateTime;

}
