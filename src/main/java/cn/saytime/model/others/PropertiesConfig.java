package cn.saytime.model.others;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: 92090
 * Date: 2018/4/11
 * Time: 16:25
 * Description:
 */
@Component
public class PropertiesConfig {

    @Value("${test.user.username}")
    private String username;

    @Value("${test.user.age}")
    private String age;

    @Value("${test.user.toString}")
    private String toString;

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public String getToString() {
        return toString;
    }
}