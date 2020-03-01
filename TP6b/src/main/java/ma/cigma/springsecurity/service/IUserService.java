package ma.cigma.springsecurity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.cigma.springsecurity.domaine.MyActionVo;
import ma.cigma.springsecurity.domaine.RoleVo;
import ma.cigma.springsecurity.domaine.UserVo;

public interface IUserService extends UserDetailsService{
	void save(UserVo user);
	void save(RoleVo role);
	void save(MyActionVo myAction);
	List<UserVo> getAllUsers();
	List<RoleVo> getAllRoles();
	RoleVo getRoleByName(String role);
	List<MyActionVo> getAllActions();
	void cleanDataBase();
}
