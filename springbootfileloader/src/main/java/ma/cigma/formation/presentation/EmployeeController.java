package ma.cigma.formation.presentation;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.cigma.formation.domaine.DataBaseFileVo;
import ma.cigma.formation.domaine.EmployeeVo;
import ma.cigma.formation.presentation.exception.FileStorageException;
import ma.cigma.formation.service.IService;

@RestController
public class EmployeeController {
	@Autowired
	private IService service;

	@GetMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public List<EmployeeVo> getAll() {
		return service.getAll();
	}

	@GetMapping(value = "/employees/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public EmployeeVo getById(@PathVariable(value = "id") Long id) {
		return service.getById(id);
	}

	@GetMapping("/downloadDiplome/{id}")
	public ResponseEntity<Resource> downloadDiplome(@PathVariable Long id) {
		EmployeeVo employeeVo = service.getById(id);
		DataBaseFileVo diplome = employeeVo.getDiplome();

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(diplome.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + diplome.getFileName() + "\"")
				.body(new ByteArrayResource(diplome.getData()));
	}

	@PostMapping(value = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> create(@RequestPart("emp") EmployeeVo emp,@RequestPart("file") MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			emp.setDiplome(new DataBaseFileVo(fileName, file.getContentType(), file.getBytes()));
			service.save(emp);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		return new ResponseEntity<String>("Employee created with success", HttpStatus.CREATED);
	}

	@PutMapping(value = "/employees/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> update(@PathVariable(value = "id") Long id, @RequestBody EmployeeVo emp,
			@RequestParam("file") MultipartFile file) {
		emp.setId(id);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			emp.setDiplome(new DataBaseFileVo(fileName, file.getContentType(), file.getBytes()));
			service.save(emp);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
		return new ResponseEntity<String>("Employee updated with success", HttpStatus.OK);
	}

	@DeleteMapping(value = "/employees/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
		service.remove(id);
		return new ResponseEntity<String>("Employee fremoved with success", HttpStatus.OK);
	}

}
