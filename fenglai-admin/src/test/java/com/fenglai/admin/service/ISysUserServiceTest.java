package com.fenglai.admin.service;

import com.fenglai.admin.pojo.dtos.AddUserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ISysUserServiceTest {

    @Autowired
    ISysUserService iSysUserService;

    @Test
    void addUser() {
        AddUserDTO addUserDTO = new AddUserDTO()
                .setUserName("小明")
                .setNickName("小明")
                .setPhone("123");
        Assertions.assertTrue(iSysUserService.addUser(addUserDTO));
    }
}