package ma.cigma.springsecurity.domaine;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyActionVo {
	private Long id;
	private String action;
	private List<RoleVo> roles=new ArrayList<>();
	public MyActionVo(String action, List<RoleVo> roles) {
		super();
		this.action = action;
		this.roles = roles;
	}

}
