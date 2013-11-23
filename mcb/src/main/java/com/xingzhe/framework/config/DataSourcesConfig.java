package com.xingzhe.framework.config;

import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourcesConfig
{
    private Logger logger = LoggerFactory.getLogger(DataSourcesConfig.class);
    
    @Value("#{jdbc['jdbc.jdbcUrl']}")
    private String jdbcUrl;
    
    @Value("#{jdbc['jdbc.user']}")
    private String user;
    
    @Value("#{jdbc['jdbc.password']}")
    private String password;
    
    @Value("#{jdbc['jdbc.filter']}")
    private String filter;
    
    @Value("#{jdbc['jdbc.maxPoolSize']}")
    private int maxPoolSize;
    
    @Value("#{jdbc['jdbc.minPoolSize']}")
    private int minPoolSize;
    
    @Value("#{jdbc['jdbc.initialpoolsize']}")
    private int initialpoolsize;
    
    @Value("#{jdbc['jdbc.maxWait']}")
    private int maxWait;
    
    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    public DruidDataSource dataSource()
    {
        logger.debug("初始化DruidDataSource 数据库连接池");
        
        DruidDataSource dataSource = new DruidDataSource();
        try
        {
            /** 基本属性 url、user、password */
            // dataSource.setDriverClassName(pro.getProperty("jdbc.driverClass"));
            dataSource.setUrl(jdbcUrl);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            
            // filter 名称 缩写 描述
            // com.alibaba.druid.filter.stat.StatFilter stat
            // 监控统计，通过StatFilter能够统计每个SQL的执行情况，包括执行时间等
            // com.alibaba.dragoon.patrol.web.PatrolWebJdbcStatFilter webstat
            // 这个是阿里巴巴Dragoon系统的filter，监控每个URL请求的JDBC访问情况
            // com.alibaba.druid.wall.WallFilter wall
            // 这个是防御SQL注入的Filter。WallFilter是在Druid 0.2以上版本才提供
            dataSource.setFilters(filter);
            
            /** 配置初始化大小、最小、最大 */
            dataSource.setMaxActive(maxPoolSize);
            dataSource.setMinIdle(minPoolSize);
            dataSource.setInitialSize(initialpoolsize);
            
            /** 配置获取连接等待超时的时间 */
            dataSource.setMaxWait(maxWait);
            
            /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
            dataSource.setTimeBetweenEvictionRunsMillis(60000);
            /** 配置一个连接在池中最小生存的时间，单位是毫秒 */
            dataSource.setMinEvictableIdleTimeMillis(300000);
            dataSource.setValidationQuery("SELECT 'x'");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(false);
            dataSource.setTestOnReturn(false);
            
            /** 打开PSCache，并且指定每个连接上PSCache的大小 */
            dataSource.setPoolPreparedStatements(true);
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(200);
            
            /** 打开removeAbandoned功能 */
            dataSource.setRemoveAbandoned(true);
            /** 30分钟 */
            dataSource.setRemoveAbandonedTimeout(1800);
            /** 关闭abanded连接时输出错误日志 */
            dataSource.setLogAbandoned(true);
            
            // dataSource.setConnectionProperties("druid.stat.mergeSql=true");
            // dataSource.setConnectionProperties("druid.stat.slowSqlMillis=3000");
            // dataSource.setConnectionProperties("druid.stat.logSlowSql=true");
            Properties properties = new Properties();
            properties.put("druid.stat.slowSqlMillis", "30000");
            properties.put("druid.stat.logSlowSql", "true");
            properties.put("druid.stat.mergeSql", "true");
            dataSource.setConnectProperties(properties);
            return dataSource;
            
        }
        catch (SQLException e)
        {
            logger.error("初始化DruidDataSource失败" + e.toString());
            return null;
        }
    }
    
    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
    //	@Bean(name="sqlSessionFactory")
    //	public  SqlSessionFactoryBean getSqlSessionFactoryBean(){
    //		SqlSessionFactoryBean sf=new SqlSessionFactoryBean();
    //		sf.setDataSource(dataSource());
    //		Resource res = new ClassPathResource("SqlMapConfig.xml");
    //		sf.setConfigLocation(res);
    //		try {
    //			sf.setMapperLocations(findAllClassPathResources("com/xingzhe/*/resultmap/*-resultmap.xml"));
    //		} catch (IOException e) {
    //			e.printStackTrace();
    //		}
    //		return sf;
    //	} 
    //	
    //	@Bean
    //	public MapperScannerConfigurer getMapperScannerConfigurer(){
    //		MapperScannerConfigurer ms=new MapperScannerConfigurer();
    //		ms.setBasePackage("com.xingzhe.**.mapper");
    //		ms.setMarkerInterface(SqlMapper.class);
    //		return ms;
    //	}
    //	
    //	private Resource[] findAllClassPathResources(String location)
    //			throws IOException {
    //		String path = location;
    //		if (path.startsWith("/")) {
    //			path = path.substring(1);
    //		}
    //		Enumeration<?> resourceUrls = getClass().getClassLoader().getResources(
    //				path);
    //		Set<Resource> result = new LinkedHashSet<Resource>(16);
    //		while (resourceUrls.hasMoreElements()) {
    //			URL url = (URL) resourceUrls.nextElement();
    //			result.add(new UrlResource(url));
    //		}
    //		return result.toArray(new Resource[result.size()]);
    //	}
    
}
