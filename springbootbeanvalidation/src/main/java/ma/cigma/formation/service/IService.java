package ma.cigma.formation.service;

import java.util.List;

import ma.cigma.formation.domaine.EmployeeVo;

public interface IService {
	List<EmployeeVo> getAll();
	EmployeeVo getById(Long id);
	void save(EmployeeVo empl);
	void remove(Long id);
	boolean isEmailExist(String email);
	List<EmployeeVo> getAll(int pageId,int size);
	List<EmployeeVo> sortBy(String fieldName);
}
