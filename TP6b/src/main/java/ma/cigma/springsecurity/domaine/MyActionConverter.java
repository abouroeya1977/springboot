package ma.cigma.springsecurity.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.springsecurity.service.model.MyAction;

public class MyActionConverter {

	public static MyActionVo toVo(MyAction bo) {
		if (bo == null)
			return null;
		MyActionVo vo = new MyActionVo();
		vo.setId(bo.getId());
		vo.setAction(bo.getAction());
		vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
		return vo;
	}

	public static MyAction toBo(MyActionVo vo) {
		if (vo == null)
			return null;
		MyAction bo = new MyAction();
		bo.setId(vo.getId());
		bo.setAction(vo.getAction());
		bo.setRoles(RoleConverter.toBoList(vo.getRoles()));
		return bo;
	}

	public static List<MyActionVo> toVoList(List<MyAction> boList) {
		if (boList == null || boList.isEmpty())
			return null;
		List<MyActionVo> voList = new ArrayList<>();
		for (MyAction MyAction : boList) {
			voList.add(toVo(MyAction));
		}
		return voList;
	}

	public static List<MyAction> toBoList(List<MyActionVo> voList) {
		if (voList == null || voList.isEmpty())
			return null;
		List<MyAction> boList = new ArrayList<>();
		for (MyActionVo MyActionVo : voList) {
			boList.add(toBo(MyActionVo));
		}
		return boList;
	}

}
