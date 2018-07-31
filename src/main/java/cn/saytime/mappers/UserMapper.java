package cn.saytime.mappers;

import cn.saytime.model.User;
import cn.saytime.framework.dao.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends GenericMapper<User> {
}
