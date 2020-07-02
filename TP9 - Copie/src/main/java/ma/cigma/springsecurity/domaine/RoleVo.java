package ma.cigma.springsecurity.domaine;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleVo {
	private int id;
	private String role;

	@Override
	public String toString() {
		return role;
	}
	public RoleVo(String role) {
		this.role = role;
	}
}