package com.nf.spring.config;

import config.EnableMybatisSpring;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Sam
 */
@Configuration
@MapperScan("com.nf.spring.dao")
@ComponentScan(value = {"com.nf.spring.service","com.nf.spring.controller","com.nf.spring.advice"})
@EnableMybatisSpring
@ImportResource(value = {"classpath:springmvc.xml","classpath:security-spring.xml"})
//@EnableWebMvc //表示开启mvc注解驱动，也就是增强spring的功能，xml配置:<mvc:annotation-driven/>
public class ApplicationContextConfig  {
}
