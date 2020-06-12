package ma.cigma.formation.service.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataBaseFile implements Serializable{
	private String fileName;
	private String fileType;
	@Lob
	private byte[] data;
}
