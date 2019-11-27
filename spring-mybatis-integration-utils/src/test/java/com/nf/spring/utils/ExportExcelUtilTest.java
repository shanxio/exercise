package com.nf.spring.utils;

import com.nf.spring.entity.UserInfo;
import config.MybatisConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExportExcelUtilTest {

    @Test
    public void ExcelTest() throws FileNotFoundException {
        ExportExcelUtil<UserInfo> util = new ExportExcelUtil<UserInfo>();
        // 准备数据
        List<UserInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            list.add(new UserInfo(1,"wer",new Date(),"34324@qq.com","123","11232413214324332142342343434524234323"));
//            list.add(new UserInfo(2,"wer",new Date(),"34324@qq.com","123","112323"));
//            list.add(new UserInfo(3,"wer",new Date(),"34324@qq.com","123","112323"));

        }
        String[] columnNames = { "ID", "姓名", "日期","邮箱","号码","密码" };
        util.exportExcel("用户表", columnNames, list, new FileOutputStream("E:/"+ UUID.randomUUID() +".xls"), ExportExcelUtil.EXCEL_FILE_2003);

    }


    @Test
    public void MybatisCofig() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MybatisConfig.class);

        DataSource dataSource = context.getBean(DataSource.class);
        dataSource.getConnection();
    }
}
