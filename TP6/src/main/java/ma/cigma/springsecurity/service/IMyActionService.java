package ma.cigma.springsecurity.service;

import java.util.List;

import ma.cigma.springsecurity.domaine.MyActionVo;

public interface IMyActionService {
	void save(MyActionVo vo);
	List<MyActionVo> getAll();

}
