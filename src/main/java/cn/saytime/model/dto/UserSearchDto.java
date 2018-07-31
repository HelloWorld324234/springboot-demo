package cn.saytime.model.dto;

import cn.saytime.framework.core.pojo.model.PaginationRequest;
import lombok.Data;

@Data
public class UserSearchDto extends PaginationRequest {

    private String name;

    private String address;

}
