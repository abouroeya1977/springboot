package ma.cigma.springsecurity.service.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class MyAction {
	@Id
	@GeneratedValue
	private Long id;
	private String action;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "MyAction_Role", joinColumns = @JoinColumn(name = "action_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

}
