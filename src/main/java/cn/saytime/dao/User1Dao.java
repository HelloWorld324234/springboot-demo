package cn.saytime.dao;

import cn.saytime.framework.dao.GenericDao;
import cn.saytime.mappers.User1Mapper;
import cn.saytime.model.User1;
import cn.saytime.model.dto.UserSearchDto;
import cn.saytime.utils.PublicUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class User1Dao extends GenericDao<User1,String> {

    private User1Mapper user1Mapper;

    public User1Dao(@Autowired User1Mapper mapper) {
        super(mapper);
        this.user1Mapper = mapper;
    }

    public List<User1> listBySearch(UserSearchDto userSearchDto) {
        if (null == userSearchDto) {
            return null;
        }
        Example example = new Example(User1.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(userSearchDto.getAddress())) {
            criteria.andLike("address", "%"+userSearchDto.getAddress()+"%");
        }
        if (StringUtil.isNotEmpty(userSearchDto.getName())) {
            criteria.andLike("name", "%"+userSearchDto.getName()+"%");
        }
        return selectByExample(example);
    }

    public List<User1> selectByUserName(String user_name) {
        Example example = new Example(User1.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("name",PublicUtil.warpLike(user_name));
        return selectByExample(example);
    }

    public boolean checkRepeat(String name) {
        Example example = new Example(User1.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        return selectCountByExample(example) != 0;
    }

}
