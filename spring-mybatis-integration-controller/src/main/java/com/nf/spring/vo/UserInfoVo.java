package com.nf.spring.vo;

import org.hibernate.validator.constraints.Length;
import com.nf.spring.validator.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Sam
 */
public class UserInfoVo {

    private Integer id;
    @Size(max = 10,min = 2,message = "字符过长或者过短")
    private String username;
    @NotNull(message = "日期不能为空")
    @Past(message = "日期输入错误")
    private Date date;
    @Email(regexp = "^[A-Za-z\\d]+[A-Za-z\\d\\-_\\.]*@([A-Za-z\\d]+[A-Za-z\\d\\-]*\\.)+[A-Za-z]{2,4}$")
    private String email;
    @NotNull(message = "号码不能为空")
//    @Pattern(regexp = "^1[3|4|5|7|8][0-9]{9}$",message = "手机号码格式错误")
    @Phone
    private String phone;
    @NotNull(message = "密码不能为空")
    @Length(min = 6,max = 6,message = "密码必须为6位")
    private String password;
    private Integer pageNum;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "id=" + id +
                ", sysUsername='" + username + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
