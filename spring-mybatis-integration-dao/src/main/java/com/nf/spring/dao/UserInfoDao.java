package com.nf.spring.dao;

import com.nf.spring.entity.Permission;
import com.nf.spring.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoDao {
    /**
     * 查询所有用户
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UserInfo> getAll(@Param("pageNum") int pageNum,@Param("pageSize") int pageSize);

    /**
     * 添加用户
     * @param userInfo
     */
    void insert(UserInfo userInfo);

    /**
     * 查询当前用户
     * @param username
     * @return
     */
    UserInfo getById(String username);

    /**
     * 查询当前所有用户的权限
     * @param username
     * @return
     */
   List<Permission> getPermissionByUsername(String username);

    /**
     * 修改密码
     * @param userInfo
     */
   void updatePassowrd(UserInfo userInfo);
}
