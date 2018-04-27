package com.taotao.manage.service.base;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
public abstract class BaseService<T extends BasePojo> {

    protected Class<T> clazz;
    @Autowired
    //泛型注入
    protected Mapper<T> mapper;

    {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];

    }

    /**
     * 根据主键查询信息
     *
     * @param id
     * @return
     */

    public T queryById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有记录
     *
     * @return
     */

    public List<T> queryAll() {
        return mapper.select(null);
    }

    /**
     * 查询一条记录
     *
     * @param record
     * @return
     */

    public T queryOne(T record) {
        return mapper.selectOne(record);
    }

    /**
     * 根据条件查询
     *
     * @param record
     * @return
     */

    public List<T> queryListByWhere(T record) {
        return mapper.select(record);
    }

    /**
     * 根据条件分页查询
     *
     * @param record
     * @param rows
     * @param page
     * @return
     */

    public PageInfo<T> queryPageListByWhere(T record, Integer rows, Integer page) {
        PageHelper.startPage(page, rows);
        List<T> list = this.mapper.select(record);
        return new PageInfo<>(list);
    }


    /**
     * 插入一条记录
     *
     * @param record
     * @return
     */
    public Integer save(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insert(record);
    }

    /**
     * 新增数据，新增不为null的字段
     *
     * @param record
     * @return
     */

    public Integer saveSelective(T record) {
        record.setCreated(new Date());
        record.setUpdated(record.getCreated());
        return mapper.insertSelective(record);
    }

    /**
     * 更新
     *
     * @param record
     * @return
     */
    public Integer update(T record) {
        record.setUpdated(new Date());
        T old = null;
        Object id = null;

        // old = mapper.selectByPrimaryKey();
        try {
            Field[] declaredFields = record.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class) && field.get(record) != null) {
                    old = mapper.selectByPrimaryKey(field.get(record));
                    id = field.get(record);
                    break;
                }
            }
            if (id == null) {
                throw new NullPointerException("修改时主键不能为null");
            }
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Object value = field.get(record);
                if (value != null) {
                    field.set(old, value);
                }

            }
            return mapper.updateByPrimaryKey(old);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 更新不为null的字段
     *
     * @param record
     * @return
     */
    public Integer updateSelective(T record) {
        record.setUpdated(new Date());
        record.setCreated(null);
        return mapper.updateByPrimaryKeySelective(record);
    }


    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    public int deleteById(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }


    public Integer deleteByIds(List<Object> ids, String property) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn(property, ids);
        return mapper.deleteByExample(example);
    }

    public int deleteByWhere(T record) {
        return mapper.delete(record);
    }

    public PageInfo<T> queryPageListOrderBy(String orderBy, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(this.clazz);
        example.setOrderByClause(orderBy);
        List<T> list = mapper.selectByExample(example);
        return new PageInfo<>(list);
    }
}
