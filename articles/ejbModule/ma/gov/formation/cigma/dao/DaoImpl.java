package ma.gov.formation.cigma.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import ma.gov.formation.cigma.service.Article;

@Stateless
public class DaoImpl implements IDao {
	
	@PersistenceContext(unitName="unite1")
	// @PersistenceContext remplace :
	//   - EntityManagerFactory factory=Persistence.createEntityManagerFactory("unite1");
	//   - EntityManager session=factory.createEntityManager();
	
	// Wildfly implémente le designe pattern IOC (Inversion Of Control)
	private EntityManager session;
	
	
//	@PersistenceContext(unitName="unite2")
//	// @PersistenceContext remplace :
//	//   - EntityManagerFactory factory=Persistence.createEntityManagerFactory("unite1");
//	//   - EntityManager session=factory.createEntityManager();
//	
//	// Wildfly implémente le designe pattern IOC (Inversion Of Control)
//	private EntityManager session2;
//	
	
//	public static void main(String[] args) {
//		
//		EntityManagerFactory factory=Persistence.createEntityManagerFactory("unite1");
//		EntityManager session=factory.createEntityManager();
//	}
	@Override
	public void save(Article a) {
		session.merge(a);
	}

	@Override
	public Article getById(Integer id) {
		return session.find(Article.class, id);
	}

	@Override
	public List<Article> getAll() {
		return session.createQuery("from Article").getResultList();
	}

	@Override
	public void remove(Integer id) {
		Article a=getById(id);
		if (a != null)
			session.remove(a);
	}
}
