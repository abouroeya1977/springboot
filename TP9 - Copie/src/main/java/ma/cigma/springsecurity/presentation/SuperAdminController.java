package ma.cigma.springsecurity.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ma.cigma.springsecurity.domaine.UserVo;
import ma.cigma.springsecurity.service.IUserService;

@Controller
public class SuperAdminController {
	@Autowired
	private IUserService userService;

	
	@GetMapping("/superadmin/list")
	public String main(Model model) {
		model.addAttribute("users", userService.getAllUsersWithOutSuperAdmin());
		model.addAttribute("userVo", new UserVo());
		return "superadmin/list";
	}
	
	
	@RequestMapping(value = "/superadmin/edit/{id}")
	public String edit(@PathVariable Long id, Model m) {
		UserVo userVo = userService.getUserById(id);
		m.addAttribute("userVo", userVo);
		m.addAttribute("allRoles", userService.getAllRolesOtherThenSuperAdmin());
		return "/superadmin/edit";
	}
	
	@RequestMapping(value = "/superadmin/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("userVo") UserVo userVo) {
		userService.save(userVo);
		return "redirect:/superadmin/list";
	}
}