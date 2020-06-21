package test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ma.gov.formation.cigma.service.Article;
import ma.gov.formation.cigma.service.IService;


public class Test {

	public static void main(String[] args) {
		InitialContext context = null;
		try {
			
			Properties props = new Properties();
			props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			context = new InitialContext(props);
			// connection JNDI établie
			String lookupName = "ejb:/articles//ServiceImpl!ma.gov.formation.cigma.service.IService";
			System.out.println(lookupName);
			IService bean = (IService) context.lookup(lookupName);
			bean.save(new Article("PC po", 12000d,120));
			bean.save(new Article("design11", 12000d,120));
			bean.save(new Article("design11", 12000d,120));
			bean.save(new Article("design11", 12000d,120));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				context.close();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	}
}
