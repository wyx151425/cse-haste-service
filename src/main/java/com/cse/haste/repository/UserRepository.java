package com.cse.haste.repository;

import com.cse.haste.model.pojo.User;
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
}
