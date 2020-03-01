package ma.cigma.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.cigma.springsecurity.dao.MyActionRepository;
import ma.cigma.springsecurity.dao.RoleRepository;
import ma.cigma.springsecurity.domaine.MyActionConverter;
import ma.cigma.springsecurity.domaine.MyActionVo;
import ma.cigma.springsecurity.service.model.MyAction;
import ma.cigma.springsecurity.service.model.Role;

@Service("actionService")
@Transactional
public class MyActionServiceImpl implements IMyActionService{
	@Autowired
	private MyActionRepository myActionRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void save(MyActionVo vo) {
		MyAction bo=MyActionConverter.toBo(vo);
		List<Role> roles=bo.getRoles();
		List<Role> rolesP=new ArrayList<>();
		for (Role role : roles) {
			Role roleP=roleRepository.findByRole(role.getRole()).get(0);
			rolesP.add(roleP);
		}
		bo.setRoles(rolesP);
		myActionRepository.save(bo);
	}

	@Override
	public List<MyActionVo> getAll() {
		return MyActionConverter.toVoList(myActionRepository.findAll());
	}

}
