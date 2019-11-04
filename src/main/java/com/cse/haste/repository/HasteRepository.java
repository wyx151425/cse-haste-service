package com.cse.haste.repository;

import java.util.List;

/**
 * 数据仓库规范
 *
 * @param <T>
 * @author WangZhenqi
 */
public interface HasteRepository<T, ID> {
    /**
     * 保存
     *
     * @param object 对象
     */
    void save(T object);

    /**
     * 删除
     *
     * @param id 对象ID
     */
    void delete(ID id);

    /**
     * 更新
     *
     * @param object 对象
     */
    void update(T object);

    /**
     * 根据ID查询对象
     *
     * @param id 对象ID
     * @return 查询到的对象
     */
    T findOneById(ID id);

    /**
     * 查询所有的数据
     *
     * @return 查询到的数据
     */
    List<T> findAll();
}
