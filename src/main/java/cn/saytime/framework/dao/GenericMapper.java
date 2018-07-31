package cn.saytime.framework.dao;

import cn.saytime.framework.core.pojo.model.IGenericModel;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Auther: yule
 * @Date: 2018/7/21
 * <E extends IGenericModel>有限制类型参数 : 后续都只能使用E进行某些判断或操作
 *
 * @param <E>
 */
public interface GenericMapper<E extends IGenericModel> extends Mapper<E> {

    @InsertProvider(
            type = InsertListProvider.class,
            method = "dynamicSQL"
    )
    int insertList(List<E> var1);

}
