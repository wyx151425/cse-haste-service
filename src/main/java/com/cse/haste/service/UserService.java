package com.cse.haste.service;

import com.cse.haste.model.pojo.User;
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
}
