package ma.cigm.springmvcrestdatajpa.service.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Emp {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Double salary;
	private String fonction;

	
	
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", salary=" + salary + ", fonction=" + fonction + "]";
	}

	public Emp() {
		super();
	}

	public Emp(String name, Double salary, String fonction) {
		super();
		this.name = name;
		this.salary = salary;
		this.fonction = fonction;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName2(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

}