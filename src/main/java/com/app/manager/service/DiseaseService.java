package com.app.manager.service;

import com.app.common.utils.R;
import com.app.manager.domain.DiseaseDO;

import java.util.List;
import java.util.Map;

/**
 * 疾病百科
 * 
 * @author fei
 * @email 1992lcg@163.com
 * @date 2020-03-28 11:19:48
 */
public interface DiseaseService {
	
	DiseaseDO get(Integer id);
	
	List<DiseaseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DiseaseDO disease);
	
	int update(DiseaseDO disease);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int readAndSave(String filePath);

	R insertDiseaseDocs();

	/**
	 * 根据症状查找疾病
	 * @return
	 */
	R searchSymptom(String symptom);
}
