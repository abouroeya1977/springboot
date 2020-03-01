package ma.cigma.springsecurity.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.springsecurity.service.model.MyAction;

public class MyActionConverter {
	public static MyAction toBo(MyActionVo vo){
		MyAction bo=new MyAction();
		bo.setId(vo.getId());
		bo.setAction(vo.getAction());
		bo.setRoles(RoleConverter.toBoList(vo.getRoles()));
		return bo;
		
	}
	
	public static List<MyAction> toBoList(List<MyActionVo> listVo) {
		 List<MyAction>  listBo=new ArrayList<>();
		 for (MyActionVo vo : listVo) {
			 listBo.add(MyActionConverter.toBo(vo));
		}
		 return listBo;
	}
	
	public static List<MyActionVo> toVoList(List<MyAction> listBo) {
		 List<MyActionVo>  listVo=new ArrayList<>();
		 for (MyAction bo : listBo) {
			 listVo.add(MyActionConverter.toVo(bo));
		}
		 return listVo;
	}

	public static MyActionVo toVo(MyAction bo){
		MyActionVo vo=new MyActionVo();
		vo.setId(bo.getId());
		vo.setAction(bo.getAction());
		vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
		return vo;
		
	}

}
