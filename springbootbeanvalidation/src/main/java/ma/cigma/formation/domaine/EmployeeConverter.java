package ma.cigma.formation.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.formation.service.model.Employee;

public class EmployeeConverter {

	public static EmployeeVo toVo(Employee bo) {
		return new EmployeeVo(bo.getId(), bo.getFirstName(), bo.getLastName(), bo.getSalary(),bo.getEmail());
	}

	public static Employee toBo(EmployeeVo vo) {
		return new Employee(vo.getId(), vo.getFirstName(), vo.getLastName(), vo.getSalary(),vo.getEmail());
	}

	public static List<EmployeeVo> toVoList(List<Employee> bos) {
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		for (Employee employee : bos) {
			result.add(toVo(employee));
		}
		return result;

	}
	
	public static List<Employee> toBoList(List<EmployeeVo> vos) {
		List<Employee> result = new ArrayList<Employee>();
		for (EmployeeVo employeeVo : vos) {
			result.add(toBo(employeeVo));
		}
		return result;

	}

}
