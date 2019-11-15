package com.cse.haste.service;

import com.cse.haste.model.pojo.User;
import com.github.pagehelper.PageInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author WangZhenqi
 */
public interface UserService {
    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return 包含主键的用户对象
     */
    User login(User user);

    /**
     * 导入用户信息
     *
     * @param file 包含用户信息的文件
     * @return 用户对象集合
     * @throws IOException            输入输出异常
     * @throws InvalidFormatException 格式错误异常
     */
    List<User> importUsers(MultipartFile file) throws IOException, InvalidFormatException;

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void disableUser(Integer id);

    /**
     * 更新用户密码
     *
     * @param user 用户对象
     */
    User updateUserPassword(User user);

    /**
     * 分页查询用户
     *
     * @param pageNum 分页页码
     * @return 查询到的用户
     */
    PageInfo<User> findAllUsers(Integer pageNum);
}
