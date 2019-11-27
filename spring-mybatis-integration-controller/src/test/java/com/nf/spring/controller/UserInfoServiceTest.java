package com.nf.spring.controller;


import com.nf.spring.entity.UserInfo;
import com.nf.spring.service.UserInfoService;
import com.nf.spring.config.ApplicationContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;
    @Test
    public void updatePassword(){
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("123456");
        userInfo.setUsername("wu");
        userInfoService.updatePassowrd(userInfo);
    }
}
