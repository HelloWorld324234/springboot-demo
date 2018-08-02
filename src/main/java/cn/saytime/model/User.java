package cn.saytime.model;

import cn.saytime.framework.core.pojo.GenericModel;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 09:06
 * @Description:
 */
@Data
public class User extends GenericModel<String> {

    private Integer uid;

    private String username;

    private String password;

    private Set<Role> roles = new HashSet<>();

    @Override
    protected String generateId() {
        return null;
    }
}
