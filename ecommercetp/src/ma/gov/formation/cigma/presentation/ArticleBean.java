package ma.gov.formation.cigma.presentation;

import java.util.List;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import ma.gov.formation.cigma.service.Article;
import ma.gov.formation.cigma.service.IService;

@ManagedBean(name = "articleBean")
@RequestScoped
public class ArticleBean {
	@EJB
	private IService service;
	private List<Article> articles;
	private Article article = new Article();

	@ManagedProperty("#{msg}")
	protected ResourceBundle bundle;
	
	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

	public List<Article> getArticles() {
		if (articles == null)
			articles = service.getAll();
		return articles;
	}

	public String addArticle() {
		service.save(article);
		return null;
	}

	public String removeArticle(Integer id) {
		service.remove(id);
		return null;
	}

	public void onRowEdit(RowEditEvent event) {
		try {
			article = (Article) event.getObject();
			addMessage("article.modifier.avec.succes", Constants.INFO_MSG);
			service.save(article);
			articles = service.getAll();
		} catch (Exception e) {
			addMessage("probleme.technique", Constants.ERROR_MSG);
			e.printStackTrace();
		}

	}
	
	public void onRowCancel(RowEditEvent event) {
		addMessage("modifications.annulees", Constants.INFO_MSG);
	}
	
	private void addMessage(String message, String severity) {
		FacesMessage msg = null;
		msg = new FacesMessage(bundle.getString(message));
		if (severity.equals(Constants.ERROR_MSG))
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		if (severity.equals(Constants.INFO_MSG))
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}


}
