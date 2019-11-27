package config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * mybatis与spring的整合注解。
 * @author Sam
 */
@Configuration
@Target({ ElementType.TYPE })
@Retention( RetentionPolicy.RUNTIME )
@Import(MybatisConfig.class)
public @interface EnableMybatisSpring {
}
