package cn.saytime.service.impl;

import cn.saytime.dao.UserDao;
import cn.saytime.exception.DemoErrorCode;
import cn.saytime.framework.core.Exception.BussinessException;
import cn.saytime.framework.core.pojo.DataTableResponse;
import cn.saytime.framework.utils.RedisUtil;
import cn.saytime.model.User;
import cn.saytime.model.dto.UserDto;
import cn.saytime.model.dto.UserResponse;
import cn.saytime.model.dto.UserSearchDto;
import cn.saytime.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public DataTableResponse<List<UserResponse>> selectAll(UserSearchDto userSearchDto) {
        DataTableResponse<List<UserResponse>> dataTableResponse = new DataTableResponse<>();
        if (null == userSearchDto) {
            return DataTableResponse.empty();
        }

        PageHelper.startPage(userSearchDto.getPageIndex(),userSearchDto.getPageSize());
        List<User> user1s = userDao.listBySearch(userSearchDto);

       // redisUtil.lSet("user1s",user1s);
        //Object users1 = redisUtil.get("user1s");

        List<UserResponse> responses = setReturn(user1s);
        PageInfo<User> pageInfo = new PageInfo<>(user1s);
        dataTableResponse.setData(responses);
        dataTableResponse.setReturnTotalItems(new Long(pageInfo.getTotal()));
        return dataTableResponse;
    }

    @Override
    public User selectById(String id) {
        @NonNull User user1 = userDao.selectByPrimaryKey(id);
        return user1;
    }

    @Override
    public boolean insert(UserDto userDto) {
        if (userDao.checkRepeat(userDto.getName())) {
            throw new BussinessException(DemoErrorCode.USER_NAME_REPEAT_EXCEPTION);
            //throw new BussinessException(0, "用户名已存在");
        }
        User user1 = new User();
        BeanUtils.copyProperties(userDto, user1);
        return userDao.insert(user1) == 1;
    }

    @Override
    public boolean update(UserDto userDto) {
        User user1 = new User();
        BeanUtils.copyProperties(userDto, user1);
        return userDao.updateByPrimaryKey(user1) != 0;
    }

    @Override
    public List<UserResponse> selectByUserName(String name) {
        List<User> user1s = userDao.selectByUserName(name);
        return setReturn(user1s);
    }

    public List<UserResponse> setReturn(List<User> user1s) {
        List<UserResponse> responses = new ArrayList<>();
        user1s.forEach(user->{
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(user,response);
            response.setId(user.getId());
            responses.add(response);
        });
        return responses;
    }

    public int insertList(List<UserDto> userDtos) {
        List<User> user1s = new ArrayList<>();
        userDtos.forEach(userDto -> {
            User user1 = new User();
            BeanUtils.copyProperties(userDto, user1);
            user1s.add(user1);
        });
        return userDao.insert(user1s);
    }

}
