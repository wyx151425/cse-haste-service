package com.cse.haste;

import com.cse.haste.model.pojo.User;
import com.cse.haste.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HasteApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addAdmin() {
        User user = User.newInstance();
        user.setName("管理员");
        user.setUsername("admin");
        user.setPassword("Cse@dmin01");
        user.setRole("ROLE_ADMIN");
        userRepository.save(user);
    }
}
