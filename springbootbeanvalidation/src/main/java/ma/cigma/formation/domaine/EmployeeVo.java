package ma.cigma.formation.domaine;

import java.io.File;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * Lombock permet de générer les geters et les seters, le constructeur par défaut, les constructeurs avec des paramètres à la compilation.
 * càd : le fichier Employee.class contiendra le code généré par Lombock
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo {
	@NotNull(message = "ID must not be empty")
	private Long id;
	@NotEmpty(message = "First name must not be empty")
	private String firstName;
	@NotEmpty(message = "Last name must not be empty")
	private String lastName;
	@NotNull(message = "Salary must not be empty")
	private Double salary;
	
	@NotNull(message = "email must not be empty")
    @Email(message = "email should be a valid email")
	@EmailCriteria(message="VOTRE PROPRE MESSAGE par rapport mail")
    private String email;
	
	
}
