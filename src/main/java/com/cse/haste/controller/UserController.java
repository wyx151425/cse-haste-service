package com.cse.haste.controller;

import com.cse.haste.context.HasteException;
import com.cse.haste.model.dto.Response;
import com.cse.haste.model.pojo.User;
import com.cse.haste.service.UserService;
import com.cse.haste.util.Constant;
import com.cse.haste.util.StatusCode;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author WangZhenqi
 */
@RestController
@RequestMapping(value = "api")
public class UserController extends HasteFacade {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "users/login")
    public Response<User> actionUserLogin(@RequestBody User requestUser) {
        User user = userService.login(requestUser);
        setCurrentUser(user);
        return new Response<>(user);
    }

    @PostMapping(value = "users/logout")
    public Response<User> actionUserLogout() {
        removeCurrentUser();
        return new Response<>();
    }

    @PostMapping(value = "users/import")
    public Response<List<User>> actionImportUsersByFile(@RequestParam(value = "file") MultipartFile file) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        if (!Constant.DocType.XLSX.equals(file.getContentType())) {
            throw new HasteException(StatusCode.FILE_FORMAT_ERROR);
        }
        List<User> users;
        try {
            users = userService.importUsers(file);
        } catch (InvalidFormatException | IOException e) {
            throw new HasteException(e, StatusCode.FILE_RESOLVE_ERROR);
        }
        return new Response<>(users);
    }

    @DeleteMapping(value = "users/{id}/disable")
    public Response<User> actionDisableUser(@PathVariable(value = "id") Integer id) {
        User user = getCurrentUser();
        if (!Constant.Roles.ADMIN.equals(user.getRole())) {
            throw new HasteException(StatusCode.USER_UNAUTHORIZED);
        }
        userService.disableUser(id);
        return new Response<>();
    }
}
