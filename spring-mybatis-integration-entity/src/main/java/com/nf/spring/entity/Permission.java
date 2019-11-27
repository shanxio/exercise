package com.nf.spring.entity;

/**
 * 权限类
 * @author Sam
 */
public class Permission {
    private Integer id;
    private String permTab;//权限标识符
    private String permName;//权限名称


    public Permission(Integer id) {
        this.id = id;
    }

    public Permission(Integer id, String permTab, String permName) {
        this.id = id;
        this.permTab = permTab;
        this.permName = permName;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permTab='" + permTab + '\'' +
                ", permName='" + permName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermTab() {
        return permTab;
    }

    public void setPermTab(String permTab) {
        this.permTab = permTab;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }
}
