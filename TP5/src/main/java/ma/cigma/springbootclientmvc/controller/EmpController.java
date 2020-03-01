package ma.cigma.springbootclientmvc.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import ma.cigma.springbootclientmvc.domaine.EmpVo;

@Controller
public class EmpController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${rest.url}")
	private String url;

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

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(emp, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);

		return "redirect:/viewemp";// will redirect to viewemp request mapping
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/viewemp, la page
	 * /WEB-INF/vues/viewemp.jsp sera affichée. La liste des employées est
	 * placée dans le Model.
	 */
	@RequestMapping("/viewemp")
	public String viewemp(Model m) {

		// HttpHeaders
		EmpVo[] list = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		// HttpEntity<String>: To get result as String.
		HttpEntity<EmpVo[]> entity = new HttpEntity<EmpVo[]>(headers);

		// Send request with GET method, and Headers.
		ResponseEntity<EmpVo[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, EmpVo[].class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);

		// Status Code: 200
		if (statusCode == HttpStatus.OK)
			list = response.getBody();
		m.addAttribute("list", Arrays.asList(list));
		return "viewemp";
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/editemp/id, la page
	 * /WEB-INF/vues/empeditform.jsp sera affichée. L'objet EmpVo est placé dans
	 * le Model.
	 */
	@RequestMapping(value = "/editemp/{id}")
	public String edit(@PathVariable Long id, Model m) {
		// HttpHeaders
		EmpVo emp = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		// HttpEntity<String>: To get result as String.
		HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(headers);

		// Send request with GET method, and Headers.
		ResponseEntity<EmpVo> response = restTemplate.exchange(url + id, HttpMethod.GET, entity, EmpVo.class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);

		// Status Code: 200
		if (statusCode == HttpStatus.OK)
			emp = response.getBody();
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
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(emp, headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);

		return "redirect:/viewemp";
	}

	/**
	 * lorsqu'on tape le lien http://localhost:8080/deleteemp/id, on récupère la
	 * valeur du paramètre id, on exécute save() et après on redirige la réponse
	 * vers la page /WEB-INF/vues/viewemp.jsp.
	 */
	@RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id) {
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(headers);

		ResponseEntity<String> response = restTemplate.exchange(url+id, HttpMethod.DELETE, entity, String.class);

		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		return "redirect:/viewemp";
	}
}