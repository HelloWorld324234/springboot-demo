package cn.saytime.framework.core.pojo.model;

import cn.saytime.framework.core.pojo.GenericModel;

import java.util.UUID;

public abstract class GenericGuidModel extends GenericModel<String> {

    @Override
    protected String generateId() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
