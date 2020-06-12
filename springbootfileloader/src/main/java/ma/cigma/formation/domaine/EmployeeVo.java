package ma.cigma.formation.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo {
	private Long id;
	private String firstName;
	private String lastName;
	private Double salary;
    private String email;
	private DataBaseFileVo diplome;
}
