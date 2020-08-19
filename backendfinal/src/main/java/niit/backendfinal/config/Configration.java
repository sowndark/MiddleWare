package niit.backendfinal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="niit")
public class Configration {

	@Bean(name="dataSource")
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource= new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl(" jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		
	return  dataSource;
	}
	public Properties getHibernateProperties()
	{
		Properties hp=new Properties();
		hp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hp.setProperty("hibernate.hbm2ddl.auto","update");
		hp.setProperty("hibernate.show-sql", "true");
		hp.setProperty("hconnection.pool_size","1");
		hp.setProperty("vache.provider_class","org.hibernate.cache.NoCacheProvider");
		hp.setProperty("hibernate.id.new_generator_mapping", "true");
		
		
		return hp;
	}
	@Bean(name="sessionFactory")
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan("niit");
		
		return sessionFactory;
		
		
	}
	@Bean(name="transactionManager")
	@Autowired
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		HibernateTransactionManager transactionManager =new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
}