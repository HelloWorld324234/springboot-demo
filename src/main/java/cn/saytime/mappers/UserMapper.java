package cn.saytime.mappers;

import cn.saytime.framework.dao.GenericMapper;
import cn.saytime.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 09:14
 * @Description:
 */
public interface UserMapper extends GenericMapper<User> {

    User findByUserName(@Param("username") String username);

}
