package config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:dbconfig.properties")
@EnableTransactionManagement //开启事务
public class MybatisConfig {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Autowired
    private DataSource dataSource;
    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(username);
        return druidDataSource;
    }

    @Autowired(required = false)
    public MybatisSpringConfiguration mybatisMapperSource;

    /**
     *mapper文件具体路径
     * @return
     */
    public String getMybatisMapperSource() {
        if (mybatisMapperSource!=null){
            return mybatisMapperSource.getMapperLocation();
        }
        return "classpath*:com/nf/spring/dao/mappers/**/*.xml";
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("reasonable","true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        Resource[] resources = resolver.getResources(getMybatisMapperSource());
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setPlugins(pageInterceptor());
        return sqlSessionFactoryBean.getObject();
    }



}
