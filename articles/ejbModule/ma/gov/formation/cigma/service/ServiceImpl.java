package ma.gov.formation.cigma.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ma.gov.formation.cigma.dao.IDao;

@Stateless
public class ServiceImpl implements IService {
	// Permet d'injecter un EJB local.
	//@EJB <=> @Autowired dans Spring
	@EJB
	private IDao dao;

	@Override
	public void save(Article a) {
		dao.save(a);
	}

	@Override
	public Article getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Article> getAll() {
		return dao.getAll();
	}

	@Override
	public void remove(Integer id) {
		dao.remove(id);
	}
}
