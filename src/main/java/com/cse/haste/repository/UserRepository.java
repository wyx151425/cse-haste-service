package com.cse.haste.repository;

import com.cse.haste.model.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WangZhenqi
 */
@Repository(value = "userRepository")
public interface UserRepository extends HasteRepository<User, Integer> {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findOneByUsername(String username);

    /**
     * 根据角色查询用户
     *
     * @param role 角色
     * @return 用户数据集合
     */
    List<User> findAllByRole(String role);

    /**
     * 根据角色查询用户
     *
     * @param role 角色
     * @param name 名称
     * @return 用户数据集合
     */
    List<User> findAllByRoleAndNameLike(@Param(value = "role") String role, @Param(value = "name") String name);

    /**
     * 查询ID在ID数组中的用户
     *
     * @param ids ID数组
     * @return 用户数据集合
     */
    List<User> findAllByIdIn(@Param(value = "ids") List<Integer> ids);

    /**
     * 查询ID不在ID数组中的用户
     *
     * @param ids ID数组
     * @return 用户数据集合
     */
    List<User> findAllByIdNotIn(@Param(value = "ids") List<Integer> ids);

    /**
     * 查询ID不在ID数组中的用户
     *
     * @param ids  ID数组
     * @param name 名称
     * @return 用户数据集合
     */
    List<User> findAllByIdNotInAndNameLike(@Param(value = "ids") List<Integer> ids, @Param(value = "name") String name);
}
