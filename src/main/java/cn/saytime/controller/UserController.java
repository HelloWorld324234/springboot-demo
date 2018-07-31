package cn.saytime.controller;

import cn.saytime.annotation.AppResponsBody;
import cn.saytime.framework.core.pojo.DataTableResponse;
import cn.saytime.framework.webapp.RestfulApiResponse;
import cn.saytime.model.User;
import cn.saytime.model.dto.UserDto;
import cn.saytime.model.dto.UserResponse;
import cn.saytime.model.dto.UserSearchDto;
import cn.saytime.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(description = "用户")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation("查询列表")
    @PostMapping("/query_users")
    @AppResponsBody
    public DataTableResponse<List<UserResponse>> selectAll(@RequestBody UserSearchDto userSearchDto) {
        return userService.selectAll(userSearchDto);
    }

    @ApiOperation("查询")
    @GetMapping("/user/{id}")
    @AppResponsBody
    public User selectById(@PathVariable("id") @NonNull String id) {
        return userService.selectById(id);
    }

    @ApiOperation("通过姓名查询")
    @GetMapping("/users/{name}")
    public RestfulApiResponse<List<UserResponse>> selectByUserName(@PathVariable("name") @NonNull String name) {
        return RestfulApiResponse.success(userService.selectByUserName(name));
    }

    @ApiOperation("新增")
    @PostMapping("/user")
    public RestfulApiResponse<Boolean> insert(@RequestBody UserDto userDto) {
        return RestfulApiResponse.success(userService.insert(userDto));
    }

    @ApiOperation("批量新增")
    @PostMapping("/add/users")
    public RestfulApiResponse<Integer> insertList(@RequestBody List<UserDto> userDtos) {
        return RestfulApiResponse.success(userService.insertList(userDtos));
    }

    @ApiOperation("更新")
    @PutMapping("/user")
    public RestfulApiResponse<Boolean> update(@RequestBody UserDto userDto) {
        return RestfulApiResponse.success(userService.update(userDto));
    }

}
