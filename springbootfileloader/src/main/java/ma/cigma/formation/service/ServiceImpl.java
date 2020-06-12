package ma.cigma.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ma.cigma.formation.dao.EmployeeRepository;
import ma.cigma.formation.domaine.EmployeeConverter;
import ma.cigma.formation.domaine.EmployeeVo;
import ma.cigma.formation.service.model.Employee;

@Service
public class ServiceImpl implements IService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<EmployeeVo> getAll() {
		List<Employee> listBos=employeeRepository.findAll();
		return EmployeeConverter.toVoList(listBos);
	}

	@Override
	public EmployeeVo getById(Long id) {
		Employee bo=employeeRepository.getOne(id);
		return EmployeeConverter.toVo(bo);
	}

	@Override
	public void save(EmployeeVo empl) {
		employeeRepository.save(EmployeeConverter.toBo(empl));
	}

	@Override
	public void remove(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public boolean isEmailExist(String email) {
		List<Employee> list=employeeRepository.findByEmail(email);
		if (list != null && !list.isEmpty())
			return true;
		return false;
	}

	@Override
	public List<EmployeeVo> getAll(int pageId, int size) {
		Page<Employee> listBos=employeeRepository.findAll(PageRequest.of(pageId, size));
		return EmployeeConverter.toVoList(listBos.getContent());
	}

	@Override
	public List<EmployeeVo> sortBy(String fieldName) {
		List<Employee> listBos=employeeRepository.findAll(Sort.by(Direction.DESC,fieldName));
		return EmployeeConverter.toVoList(listBos);
	}
}
