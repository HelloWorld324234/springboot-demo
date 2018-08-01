package cn.saytime.utils;

/**
 * @Auther: yule
 * @Date: 2018/8/1 0001 08:59
 * @Description:
 */
public class PublicUtil {

    /**
     * 给sql语句加上%
     *
     * @param var1
     * @return
     */
    public static String warpLike(String var1) {
        return "%" + var1 + "%";
    }

}
