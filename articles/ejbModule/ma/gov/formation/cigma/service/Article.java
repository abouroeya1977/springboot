package ma.gov.formation.cigma.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ARTICLE_TB")

public class Article implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	@Override
	public String toString() {
		return "Article [id=" + id + ", designation=" + designation + ", prix="
				+ prix + ", quantiteEnStock=" + quantiteEnStock + "]";
	}
	
	@Column(name="libelle")
	private String designation;
	private Double prix;
	private Integer quantiteEnStock;
	public Article() {
	}
	public Article(String designation, Double prix, Integer quantiteEnStock) {
		super();
		this.designation = designation;
		this.prix = prix;
		this.quantiteEnStock = quantiteEnStock;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public Integer getQuantiteEnStock() {
		return quantiteEnStock;
	}
	public void setQuantiteEnStock(Integer quantiteEnStock) {
		this.quantiteEnStock = quantiteEnStock;
	}
}
