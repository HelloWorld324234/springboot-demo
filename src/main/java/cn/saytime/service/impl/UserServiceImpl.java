package cn.saytime.service.impl;

import cn.saytime.dao.UserDao;
import cn.saytime.exception.DemoBizException;
import cn.saytime.exception.DemoErrorCode;
import cn.saytime.framework.core.pojo.DataTableResponse;
import cn.saytime.framework.utils.RedisUtil;
import cn.saytime.model.User;
import cn.saytime.model.dto.UserDto;
import cn.saytime.model.dto.UserResponse;
import cn.saytime.model.dto.UserSearchDto;
import cn.saytime.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        List<User> users = userDao.listBySearch(userSearchDto);

       // redisUtil.lSet("users",users);
        //Object users1 = redisUtil.get("users");

        List<UserResponse> responses = setReturn(users);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        dataTableResponse.setData(responses);
        dataTableResponse.setReturnTotalItems(new Long(pageInfo.getTotal()));
        return dataTableResponse;
    }

    @Override
    public User selectById(String id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean insert(UserDto userDto) {
        if (userDao.checkRepeat(userDto.getName())) {
            throw new DemoBizException(DemoErrorCode.USER_NAME_REPEAT_EXCEPTION);
        }
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return userDao.insert(user) == 1;
    }

    @Override
    public boolean update(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return userDao.updateByPrimaryKey(user) != 0;
    }

    @Override
    public List<UserResponse> selectByUserName(String name) {
        List<User> users = userDao.selectByUserName(name);
        return setReturn(users);
    }

    public List<UserResponse> setReturn(List<User> users) {
        List<UserResponse> responses = new ArrayList<>();
        users.forEach(user->{
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(user,response);
            response.setId(user.getId());
            responses.add(response);
        });
        return responses;
    }

    public int insertList(List<UserDto> userDtos) {
        List<User> users = new ArrayList<>();
        userDtos.forEach(userDto -> {
            User user = new User();
            BeanUtils.copyProperties(userDto,user);
            users.add(user);
        });
        return userDao.insert(users);
    }

}
