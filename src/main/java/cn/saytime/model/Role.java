package cn.saytime.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 09:08
 * @Description:
 */
@Data
public class Role {

    private Integer rid;

    private String rname;

    private Set<User> users = new HashSet<>();

    private Set<Module> modules = new HashSet<>();

}
