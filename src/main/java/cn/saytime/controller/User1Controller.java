package cn.saytime.controller;

import cn.saytime.framework.core.pojo.DataTableResponse;
import cn.saytime.framework.webapp.RestfulApiResponse;
import cn.saytime.model.User1;
import cn.saytime.model.dto.UserDto;
import cn.saytime.model.dto.UserResponse;
import cn.saytime.model.dto.UserSearchDto;
import cn.saytime.service.User1Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "用户")
@Slf4j
public class User1Controller {

    @Autowired
    private User1Service user1Service;

    @ApiOperation("查询列表")
    @PostMapping("/query_users")
    public DataTableResponse<List<UserResponse>> selectAll(@RequestBody UserSearchDto userSearchDto) {
        return user1Service.selectAll(userSearchDto);
    }

    @ApiOperation("查询")
    @GetMapping("/user/{id}")
    public User1 selectById(@PathVariable("id") @NonNull String id) {
        @NonNull User1 user1 = user1Service.selectById(id);
        log.info("name:{}, address:{}", user1.getName(), user1.getAddress());
        return user1;
    }

    @ApiOperation("通过姓名查询")
    @GetMapping("/users/{name}")
    public RestfulApiResponse<List<UserResponse>> selectByUserName(@PathVariable("name") @NonNull String name) {
        return RestfulApiResponse.success(user1Service.selectByUserName(name));
    }

    @ApiOperation("新增")
    @PostMapping("/user")
    public RestfulApiResponse<Boolean> insert(@RequestBody UserDto userDto) {
        return RestfulApiResponse.success(user1Service.insert(userDto));
    }

    @ApiOperation("批量新增")
    @PostMapping("/add/users")
    public RestfulApiResponse<Integer> insertList(@RequestBody List<UserDto> userDtos) {
        return RestfulApiResponse.success(user1Service.insertList(userDtos));
    }

    @ApiOperation("更新")
    @PutMapping("/user")
    public RestfulApiResponse<Boolean> update(@RequestBody UserDto userDto) {
        return RestfulApiResponse.success(user1Service.update(userDto));
    }

}
