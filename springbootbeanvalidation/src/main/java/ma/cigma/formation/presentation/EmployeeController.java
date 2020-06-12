package ma.cigma.formation.presentation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ma.cigma.formation.domaine.EmployeeVo;
import ma.cigma.formation.presentation.exception.EmailAlreadyExist;
import ma.cigma.formation.presentation.exception.RecordNotFoundException;
import ma.cigma.formation.service.IService;

@RestController
public class EmployeeController {
	@Autowired
	@Qualifier("h2")
	private IService service;

	
	@GetMapping(value = "/employees/sort/{fieldName}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<EmployeeVo> getAll(@PathVariable String fieldName) {
		return service.sortBy(fieldName);
	}
	
	
	
	
	@GetMapping(value = "/employees/{pageId}/{size}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<EmployeeVo> getAll(@PathVariable Integer pageId,@PathVariable Integer size) {
		return service.getAll(pageId,size);
	}
	
	

	@GetMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<EmployeeVo> getAll() {
		return service.getAll();
	}

	@GetMapping(value = "/employees/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public EmployeeVo getById(@PathVariable(value = "id") Long id) {
		EmployeeVo e = service.getById(id);
		if (e == null)
			throw new RecordNotFoundException("No record with id=" + id);
		return service.getById(id);
	}

	@PostMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> create(@Valid @RequestBody EmployeeVo emp) {
		if (service.isEmailExist(emp.getEmail())) {
			throw new EmailAlreadyExist("Email already exist : " + emp.getEmail());
		}
		service.save(emp);
		return new ResponseEntity<String>("Employee created with success", HttpStatus.CREATED);
	}

	@PutMapping(value = "/employees/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> update(@Valid @PathVariable(value = "id") Long id, @RequestBody EmployeeVo emp) {
		EmployeeVo e = service.getById(id);
		if (e == null)
			throw new RecordNotFoundException("No record with id=" + id);
		emp.setId(id);
		service.save(emp);
		return new ResponseEntity<String>("Employee updated with success", HttpStatus.OK);
	}

	@DeleteMapping(value = "/employees/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		EmployeeVo e = service.getById(id);
		if (e == null)
			throw new RecordNotFoundException("No record with id=" + id);
		service.remove(id);
		return new ResponseEntity<String>("Employee fremoved with success", HttpStatus.OK);
	}

}
