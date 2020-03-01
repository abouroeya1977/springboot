package ma.cigm.springmvcrestdatajpa.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.cigm.springmvcrestdatajpa.domaine.EmpVo;
import ma.cigm.springmvcrestdatajpa.service.IService;

@Controller
public class EmpController {

	@Autowired
	private IService service;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile(Model m) {
		return "index";
	}

	/**
	 * Permet d'afficher la page /WEB-INF/vues/empform.jsp. L'objet qui est
	 * passé dans la requête est "employe" de type la classe EmpVo. Les
	 * attributs de l'objet "employe" seront accessibles au niveau de la page
	 * moyennant les gettets et les setters.
	 */
	@RequestMapping("/empform")
	public String showform(Model m) {
		m.addAttribute("empVo", new EmpVo());
		return "empform";
	}

	/**
	 * 1°) Au niveau du formulaire "empform.jsp", lorsqu'on clique sur le bouton
	 * Submit, l'action "/save" sera exécutée. Les valeurs du formulaires seront
	 * passés dans l'objet EmpVo. Ici, il faut préciser que la méthode HTTP est
	 * bien POST car la méthode par défaut est GET.
	 * 
	 * 2°) la méthode save() de l'interface IService sera lancée. 3°) Ensuite la
	 * réponse sera redirigée vers la page /WEB-INF/vues/viewemp.jsp
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("empVo") EmpVo emp) {
		service.save(emp);
		return "redirect:/viewemp";// will redirect to viewemp request mapping
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/viewemp, la page
	 * /WEB-INF/vues/viewemp.jsp sera affichée. La liste des employées est
	 * placée dans le Model.
	 */
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {
		List<EmpVo> list = service.getEmployees();
		m.addAttribute("list", list);
		return "viewemp";
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/editemp/id, la page
	 * /WEB-INF/vues/empeditform.jsp sera affichée. L'objet EmpVo est placé dans
	 * le Model.
	 */
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable Long id, Model m) {
		EmpVo emp = service.getEmpById(id);
		m.addAttribute("empVo", emp);
		return "empeditform";
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/editsave, l'objet EmpVo est
	 * passé dans la reqûete, ensuite on exécute la méthode save(). Ensuite, on
	 * redirige la réponse vers la page /WEB-INF/vues/viewemp.jsp. Ici, il faut
	 * préciser la méthode POST.
	 */
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") EmpVo emp) {
		service.save(emp);
		return "redirect:/viewemp";
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/deleteemp/id, on récupère la
	 * valeur du paramètre id, on exécute save() et après on redirige la réponse
	 * vers la page /WEB-INF/vues/viewemp.jsp.
	 */
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/viewemp";
	}

	/**
	 * Chercher la liste des employés ayant le même salaire
	 */
	@RequestMapping("/salary/{salary}")
	public String getBySalary(@PathVariable Double salary, Model m) {
		List<EmpVo> list = service.findBySalary(salary);
		m.addAttribute("list", list);
		return "viewemp";
	}

	/**
	 * Chercher la liste des employés ayant la même fonction
	 */
	@RequestMapping("/fonction/{fonction}")
	public String getByFonction(@PathVariable String fonction, Model m) {
		List<EmpVo> list = service.findByFonction(fonction);
		m.addAttribute("list", list);
		return "viewemp";
	}

	/**
	 * Chercher la liste des employés ayant le même salaire et la même fonction
	 */
	@RequestMapping("/salary_and_fonction/{salary}/{fonction}")
	public String getBySalaryAndFonction(@PathVariable Double salary, @PathVariable String fonction, Model m) {
		List<EmpVo> list = service.findBySalaryAndFonction(salary, fonction);
		m.addAttribute("list", list);
		return "viewemp";
	}

	/**
	 * Chercher l'employé qui le grand salaire
	 */
	@RequestMapping("/max_salary")
	public String getMaxSalary(Model m) {
		EmpVo empVo = service.getEmpHavaingMaxSalary();
		List<EmpVo> list = new ArrayList<>();
		list.add(empVo);
		m.addAttribute("list", list);
		return "viewemp";
	}

	/**
	 * Afficher la liste des employés en utilisant la pagination
	 */
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<EmpVo> list = service.findAll(pageid, size);
		m.addAttribute("list", list);
		return "viewemp";
	}

	/**
	 * Trier les employés par le nom de champs qu'on passe dans l'URL
	 */
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<EmpVo> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "viewemp";
	}
}