package egen.tracker;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class Config {

	LocalContainerEntityManagerFactoryBean initEMF() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(getDataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan("egen.tracker.entity");
		return emf;
	}

	Properties initProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.show_sql", "true");
		return properties;
	}

	DriverManagerDataSource initDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:mysql://localhost/tracker?useSSL=false", "root",
				"toor");
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/tracker?useSSL=false");
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean emf() {
		Properties properties = initProperties();
		LocalContainerEntityManagerFactoryBean emf = initEMF();
		emf.setJpaProperties(properties);
		return emf;
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = initDataSource();
		return ds;
	}

	@Bean
	public PlatformTransactionManager tm(EntityManagerFactory emf) {
		JpaTransactionManager jtm = new JpaTransactionManager(emf);
		return jtm;
	}
}
