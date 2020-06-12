package ma.cigma.formation.domaine;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBaseFileVo implements Serializable{
	private String fileName;
	private String fileType;
	private byte[] data;
}
