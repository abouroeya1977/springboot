package ma.cigma.formation.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.formation.service.model.DataBaseFile;

public class DataBaseFileConverter {

	public static DataBaseFileVo toVo(DataBaseFile bo) {
		return new DataBaseFileVo(bo.getFileName(), bo.getFileType(), bo.getData());
	}

	public static DataBaseFile toBo(DataBaseFileVo vo) {
		return new DataBaseFile(vo.getFileName(), vo.getFileType(), vo.getData());
	}

	public static List<DataBaseFileVo> toVoList(List<DataBaseFile> bos) {
		List<DataBaseFileVo> result = new ArrayList<DataBaseFileVo>();
		for (DataBaseFile dataBaseFile : bos) {
			result.add(toVo(dataBaseFile));
		}
		return result;

	}
	
	public static List<DataBaseFile> toBoList(List<DataBaseFileVo> vos) {
		List<DataBaseFile> result = new ArrayList<DataBaseFile>();
		for (DataBaseFileVo dataBaseFileVo : vos) {
			result.add(toBo(dataBaseFileVo));
		}
		return result;

	}

}
