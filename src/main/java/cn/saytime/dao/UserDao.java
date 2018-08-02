package cn.saytime.dao;

import cn.saytime.framework.dao.GenericDao;
import cn.saytime.mappers.UserMapper;
import cn.saytime.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 11:20
 * @Description:
 */
@Repository
public class UserDao extends GenericDao<User, String> {

    private UserMapper userMapper;

    @Autowired
    public UserDao(UserMapper mapper) {
        super(mapper);
        this.userMapper = mapper;
    }

    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
