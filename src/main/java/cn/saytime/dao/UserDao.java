package cn.saytime.dao;

import cn.saytime.framework.dao.GenericDao;
import cn.saytime.mappers.UserMapper;
import cn.saytime.model.User;
import cn.saytime.model.dto.UserSearchDto;
import cn.saytime.utils.PublicUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserDao extends GenericDao<User,String> {

    private UserMapper userMapper;

    public UserDao(@Autowired UserMapper mapper) {
        super(mapper);
        this.userMapper = mapper;
    }

    public List<User> listBySearch(UserSearchDto userSearchDto) {
        if (null == userSearchDto) {
            return null;
        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(userSearchDto.getAddress())) {
            criteria.andLike("address", "%"+userSearchDto.getAddress()+"%");
        }
        if (StringUtil.isNotEmpty(userSearchDto.getName())) {
            criteria.andLike("name", "%"+userSearchDto.getName()+"%");
        }
        return selectByExample(example);
    }

    public List<User> selectByUserName(String user_name) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name",PublicUtil.warpLike(user_name));
        return selectByExample(example);
    }

    public boolean checkRepeat(String name) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        return selectCountByExample(example) != 0;
    }

}
