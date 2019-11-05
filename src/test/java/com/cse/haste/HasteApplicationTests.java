package com.cse.haste;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HasteApplicationTests {

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EvaluateeRepository evaluateeRepository;

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void addAdmin() {
//        User user = User.newInstance();
//        user.setName("管理员");
//        user.setUsername("admin");
//        user.setPassword("Cse@dmin01");
//        user.setRole("ROLE_ADMIN");
//        userRepository.save(user);
//    }
//
//    @Test
//    public void addEvaluatee() {
//        Evaluatee evaluatee = Evaluatee.newInstance();
//        User user = new User();
//        user.setName("领导班子");
//        evaluatee.setUser(user);
//        evaluateeRepository.save(evaluatee);
//    }
}
