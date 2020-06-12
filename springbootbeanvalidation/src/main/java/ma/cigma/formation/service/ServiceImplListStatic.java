package ma.cigma.formation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ma.cigma.formation.domaine.EmployeeVo;

@Service
@Qualifier("static")
public class ServiceImplListStatic implements IService {
	private static List<EmployeeVo> employees = new ArrayList<>();

	@Override
	public List<EmployeeVo> getAll() {
		return employees;
	}

	@Override
	public EmployeeVo getById(Long id) {
		if ((employees == null) || employees.isEmpty())
			return null;
		for (EmployeeVo employee : employees) {
			if (employee.getId().equals(id))
				return employee;
		}
		return null;
	}

	@Override
	public void save(EmployeeVo e) {
		EmployeeVo emp = getById(e.getId());
		if (emp == null) {
			employees.add(e);
			return;
		}
		employees.remove(emp);
		employees.add(e);
	}

	@Override
	public void remove(Long id) {
		EmployeeVo emp = getById(id);
		if (emp == null) {
			return;
		}
		employees.remove(id);
	}

	@Override
	public boolean isEmailExist(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<EmployeeVo> getAll(int pageId, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EmployeeVo> sortBy(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
}
