package cn.saytime.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: yule
 * @Date: 2018/8/2 0002 09:09
 * @Description:
 */
@Data
public class Module {

    private Integer mid;

    private String mname;

    private Set<Role> roles = new HashSet<>();

}
