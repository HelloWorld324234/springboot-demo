package cn.saytime.service;

import cn.saytime.model.User;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 11:33
 * @Description:
 */
public interface UserService {

    User findByUserName(String username);

}
