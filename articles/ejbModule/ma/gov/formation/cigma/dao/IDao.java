package ma.gov.formation.cigma.dao;

import java.util.List;

import javax.ejb.Local;

import ma.gov.formation.cigma.service.Article;

@Local
public interface IDao {
	public void save(Article a);
	public Article getById(Integer id);
	public List<Article> getAll();
	public void remove(Integer id);
}
