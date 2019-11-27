package com.nf.spring.service.impl;

import com.nf.spring.dao.UserInfoDao;
import com.nf.spring.entity.Permission;
import com.nf.spring.entity.UserInfo;
import com.nf.spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getAll(int pageNum, int pageSize) {

        return userInfoDao.getAll(pageNum,pageSize);
    }

    @Override
    public void insert(UserInfo userInfo) {
        userInfoDao.insert(userInfo);
    }

    @Override
    public UserInfo getById(String username) {
        return userInfoDao.getById(username);
    }

    @Override
    public List<Permission> getPermissionByUsername(String username) {
        return userInfoDao.getPermissionByUsername(username);
    }

    @Override
    public void updatePassowrd(UserInfo userInfo) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoDao.updatePassowrd(userInfo);
    }
}
