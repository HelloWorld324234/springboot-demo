package cn.saytime.service;

import cn.saytime.framework.core.pojo.DataTableResponse;
import cn.saytime.model.User1;
import cn.saytime.model.dto.UserDto;
import cn.saytime.model.dto.UserResponse;
import cn.saytime.model.dto.UserSearchDto;

import java.util.List;

public interface User1Service {

    DataTableResponse<List<UserResponse>> selectAll(UserSearchDto userSearchDto);

    User1 selectById(String id);

    boolean insert(UserDto userDto);

    boolean update(UserDto userDto);

    List<UserResponse> selectByUserName(String name);

    public int insertList(List<UserDto> userDtos);
}
