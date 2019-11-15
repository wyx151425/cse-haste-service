package com.cse.haste;

import com.cse.haste.model.pojo.User;
import com.cse.haste.repository.UserRepository;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
//        User admin = User.newInstance();
//        admin.setName("管理员");
//        admin.setUsername("admin");
//        admin.setPassword("Cse@dmin000");
//        admin.setRole("ROLE_ADMIN");
//        userRepository.save(admin);
//
//        User admin01 = User.newInstance();
//        admin01.setName("管理员01");
//        admin01.setUsername("admin01");
//        admin01.setPassword("123456");
//        admin01.setRole("ROLE_ADMIN");
//        userRepository.save(admin01);
//
//        User admin02 = User.newInstance();
//        admin02.setName("管理员02");
//        admin02.setUsername("admin02");
//        admin02.setPassword("123456");
//        admin02.setRole("ROLE_ADMIN");
//        userRepository.save(admin02);
//
//        User admin03 = User.newInstance();
//        admin03.setName("管理员03");
//        admin03.setUsername("admin03");
//        admin03.setPassword("123456");
//        admin03.setRole("ROLE_ADMIN");
//        userRepository.save(admin03);
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

//    @Test
//    public void test() throws IOException, InvalidFormatException {
//        InputStream in = new FileInputStream("excel.xlsx");
//        Workbook workbook = WorkbookFactory.create(in);
//        Sheet sheet = workbook.getSheet("users");
//
//        CellStyle cellStyle = workbook.createCellStyle();
//        DataFormat format = workbook.createDataFormat();
//        cellStyle.setDataFormat(format.getFormat("@"));
//        for (Row row : sheet) {
//            for (Cell cell : row) {
//                cell.setCellStyle(cellStyle);
//                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            }
//        }
//
//        for (Row row : sheet) {
//            User user = userRepository.findOneByUsername(row.getCell(1).toString());
//            if (user != null) {
//                user.setPassword(row.getCell(2).toString());
//                userRepository.update(user);
//            }
//        }
//    }
}
