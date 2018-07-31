package cn.saytime.framework.dao;

import cn.saytime.framework.core.pojo.model.IGenericModel;
import cn.saytime.framework.dao.Exception.DaoBizException;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * 通用数据库访问接口
 *
 * @param <E> 一般为实体类对象
 * @param <PK> 一般为主键类型，即String类型
 */
public abstract class GenericDao<E extends IGenericModel, PK> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected GenericMapper<E> mapper;
    private Class<E> entityClass;

    public GenericDao(GenericMapper<E> mapper) {
        this.mapper = mapper;
        this.entityClass = (Class)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public int deleteByPrimaryKey(PK pk) {
        try {
            return this.mapper.deleteByPrimaryKey(pk);
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.DELETE_EXCEPTION);
        }
    }

    public int delete(E entity) {
        try {
            return this.mapper.delete(entity);
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.DELETE_EXCEPTION);
        }
    }

    public int insert(E entity) {
        try {
            this.setInsertDefault(entity);
            return this.mapper.insert(entity);
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.INSERT_EXCEPTION);
        }
    }

    public int insertSelective(E entity) {
        try {
            this.setInsertDefault(entity);
            return this.mapper.insertSelective(entity);
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.INSERT_EXCEPTION);
        }
    }

    public int insert(Collection<E> entities) {
        if(CollectionUtils.isEmpty(entities)) {
            return 0;
        } else {
            try {
                Iterator e = entities.iterator();

                while(e.hasNext()) {
                    IGenericModel bizEx1 = (IGenericModel)e.next();
                    this.setInsertDefault((E) bizEx1);
                }

                return this.mapper.insertList(new ArrayList(entities));
            } catch (Exception var4) {
                throw new DaoBizException(DaoErrorCode.INSERT_EXCEPTION);
            }
        }
    }

    public List<E> selectAll() {
        try {
            return this.mapper.selectAll();
        } catch (Exception var3) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public E selectByPrimaryKey(PK pk) {
        try {
            return (E) this.mapper.selectByPrimaryKey(pk);
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public List<E> selectByIds(Collection<PK> ids) {
        return this.selectByIds(ids, true);
    }

    public List<E> selectByIds(Collection<PK> ids, boolean boundary) {
        try {
            if(CollectionUtils.isEmpty(ids)) {
                throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
            } else {
                Example e = this.buildIdsExample(ids);
                return this.selectByExample(e, boundary);
            }
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public int selectCount(E criteria) {
        return this.selectCount(criteria, true);
    }

    public int selectCount(E criteria, boolean boundary) {
        if(boundary) {
            this.setBoundary(criteria);
        }

        try {
            return this.mapper.selectCount(criteria);
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public List<E> select(E criteria) {
        return this.select(criteria, true);
    }

    public List<E> select(E criteria, boolean boundary) {
        if(boundary) {
            this.setBoundary(criteria);
        }

        try {
            return this.mapper.select(criteria);
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public E selectOne(E criteria) {
        return this.selectOne(criteria, true);
    }

    public E selectOne(E criteria, boolean boundary) {
        if(boundary) {
            this.setBoundary(criteria);
        }

        try {
            return (E) this.mapper.selectOne(criteria);
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public int updateByPrimaryKey(E entity) {
        try {
            if(entity.getId() == null) {
                throw new IllegalArgumentException("id不能为空");
            } else {
                this.setUpdateDefault(entity);
                return this.mapper.updateByPrimaryKey(entity);
            }
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.UPDATE_EXCEPTION);
        }
    }

    public int updateByPrimaryKeySelective(E entity) {
        try {
            if(entity.getId() == null) {
                throw new IllegalArgumentException("id不能为空");
            } else {
                this.setUpdateDefault(entity);
                return this.mapper.updateByPrimaryKeySelective(entity);
            }
        } catch (Exception var4) {
            throw new DaoBizException(DaoErrorCode.UPDATE_EXCEPTION);
        }
    }

    public int deleteByExample(Example example) {
        return this.deleteByExample(example, true);
    }

    public int deleteByExample(Example example, boolean boundary) {
        if(boundary) {
            this.setBoundary(example);
        }

        try {
            return this.mapper.deleteByExample(example);
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.DELETE_EXCEPTION);
        }
    }

    public int deleteByIds(Collection<PK> ids) {
        return this.deleteByIds(ids, true);
    }

    public int deleteByIds(Collection<PK> ids, boolean boundary) {
        if(CollectionUtils.isEmpty(ids)) {
            throw new DaoBizException(DaoErrorCode.DELETE_EXCEPTION);
        } else {
            Example example = this.buildIdsExample(ids);
            return this.deleteByExample(example, boundary);
        }
    }

    public List<E> selectByExample(Example example) {
        return this.selectByExample(example, true);
    }

    public List<E> selectByExample(Example example, boolean boundary) {
        if(boundary) {
            this.setBoundary(example);
        }

        try {
            return this.mapper.selectByExample(example);
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public int selectCountByExample(Example example) {
        return this.selectCountByExample(example, true);
    }

    public int selectCountByExample(Example example, boolean boundary) {
        if(boundary) {
            this.setBoundary(example);
        }

        try {
            return this.mapper.selectCountByExample(example);
        } catch (Exception var5) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public int updateByExample(E entity, Example example) {
        return this.updateByExample(entity, example, true);
    }

    public int updateByExample(E entity, Example example, boolean boundary) {
        if(boundary) {
            this.setBoundary(example);
        }

        try {
            this.setUpdateDefault(entity);
            return this.mapper.updateByExample(entity, example);
        } catch (Exception var6) {
            throw new DaoBizException(DaoErrorCode.UPDATE_EXCEPTION);
        }
    }

    public int updateByExampleSelective(E entity, Example example) {
        return this.updateByExampleSelective(entity, example, true);
    }

    public int updateByExampleSelective(E entity, Example example, boolean boundary) {
        if(boundary) {
            this.setBoundary(example);
        }

        try {
            this.setUpdateDefault(entity);
            return this.mapper.updateByExampleSelective(entity, example);
        } catch (Exception var6) {
            throw new DaoBizException(DaoErrorCode.UPDATE_EXCEPTION);
        }
    }

    public List<E> selectByExampleAndRowBounds(Example example, RowBounds rowBounds) {
        return this.selectByExampleAndRowBounds(example, rowBounds, true);
    }

    public List<E> selectByExampleAndRowBounds(Example example, RowBounds rowBounds, boolean boundary) {
        if(boundary) {
            this.setBoundary(example);
        }

        try {
            return this.mapper.selectByExampleAndRowBounds(example, rowBounds);
        } catch (Exception var6) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    public List<E> selectByRowBounds(E criteria, RowBounds rowBounds) {
        return this.selectByRowBounds(criteria, rowBounds, true);
    }

    public List<E> selectByRowBounds(E criteria, RowBounds rowBounds, boolean boundary) {
        if(boundary) {
            this.setBoundary(criteria);
        }

        try {
            return this.mapper.selectByRowBounds(criteria, rowBounds);
        } catch (Exception var6) {
            throw new DaoBizException(DaoErrorCode.SELECT_EXCEPTION);
        }
    }

    protected String currentUserId() {
        //return BizDataInterface.getBean().currentUserId();
        return "currentUserId";
    }

    protected void setInsertDefault(E entity) {
        entity.init();
        entity.setInsertUser(this.currentUserId());
    }

    protected void setUpdateDefault(E entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateUser(this.currentUserId());
    }

    protected void setBoundary(E criteria) {
    }

    protected void setBoundary(Example example) {
    }

    protected Example buildIdsExample(Collection<PK> ids) {
        Example example = new Example(this.entityClass);
        Set<EntityColumn> pkColumns = EntityHelper.getPKColumns(this.entityClass);
        Example.Criteria criteria = example.createCriteria();
        if(CollectionUtils.isEmpty(pkColumns)) {
            criteria.andIn("id", ids);
        } else {
            pkColumns.forEach((pkColumn) -> {
                criteria.andIn(pkColumn.getProperty(), ids);
            });
        }

        return example;
    }

    /*private String getErrorMsg(BizException bizEx) {
        return bizEx.getErrorCode().getInternationalMessage(Language.ZH);
    }*/

   /* protected void doIfNotNULL(Object object, Action action) {
        if(object != null) {
            action.doAction();
        }

    }*/
}
