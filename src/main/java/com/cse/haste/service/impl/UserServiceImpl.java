package com.cse.haste.service.impl;

import com.alibaba.fastjson.JSON;
import com.cse.haste.context.HasteException;
import com.cse.haste.model.pojo.EvaluationPlan;
import com.cse.haste.model.pojo.User;
import com.cse.haste.repository.UserRepository;
import com.cse.haste.service.UserService;
import com.cse.haste.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WangZhenqi
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User login(User user) {
        // 根据手机号检查用户是否存在
        User targetUser = userRepository.findOneByUsername(user.getUsername());
        if (null == targetUser) {
            throw new HasteException(StatusCode.USER_UNREGISTER);
        }

        // 检查用户的账号是否处于被禁用的状态
        if (Constant.Status.DISABLED == targetUser.getStatus()) {
            throw new HasteException(StatusCode.USER_DISABLED);
        }

        // 校验用户的密码是否正确
        if (targetUser.getPassword().equals(user.getPassword())) {
            // 密码校验通过后，创建TOKEN
            String token = GeneratorUtil.getToken();
            targetUser.setToken(token);

            // 删除旧TOKEN
            String oldToken = GuavaCacheUtil.getOne(String.valueOf(targetUser.getId()));
            if (null != oldToken) {
                GuavaCacheUtil.removeOne(oldToken);
            }

            // 保存新TOKEN
            GuavaCacheUtil.setOne(String.valueOf(targetUser.getId()), token);
            GuavaCacheUtil.setOne(token, JSON.toJSONString(targetUser));

            targetUser.setPassword(null);
            return targetUser;
        } else {
            throw new HasteException(StatusCode.USER_PASSWORD_ERROR);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> importUsers(MultipartFile file) throws IOException, InvalidFormatException {
        Sheet sheet = FileUtil.formatExcel(file, "users");
        List<User> users = new ArrayList<>();
        int index = 0;
        for (Row row : sheet) {
            index++;
            if (1 == index) {
                continue;
            }
            if (null != row.getCell(1) && !"".equals(row.getCell(1).toString().trim())) {
                String username = row.getCell(1).toString().trim();
                User user = userRepository.findOneByUsername(username);
                if (null == user) {
                    user = User.newInstance();
                    user.setName(row.getCell(0).toString().trim());
                    user.setUsername(username);
                    user.setPassword(row.getCell(2).toString().trim());
                    user.setRole(Constant.Roles.USER);
                    userRepository.save(user);
                }
                users.add(user);
            }
        }
        return users;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableUser(Integer id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUserPassword(User user) {
        User target = userRepository.findOneById(user.getId());
        target.setPassword(user.getPassword());
        userRepository.update(target);

        return userRepository.findOneById(user.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public PageInfo<User> findAllUsers(Integer pageNum) {
        PageHelper.startPage(pageNum, 20);
        List<User> users = userRepository.findAll();
        return new PageInfo<>(users);
    }
}
