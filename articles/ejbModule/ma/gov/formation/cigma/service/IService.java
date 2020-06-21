package ma.gov.formation.cigma.service;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface IService {
	public void save(Article a);
	public Article getById(Integer id);
	public List<Article> getAll();
	public void remove(Integer id);
}
