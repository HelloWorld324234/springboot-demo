package cn.saytime.service.impl;

import cn.saytime.model.User;
import cn.saytime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 11:35
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public User findByUserName(String username) {
        return userService.findByUserName(username);
    }
}
