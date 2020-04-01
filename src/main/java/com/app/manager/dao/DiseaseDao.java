package com.app.manager.dao;

import com.app.manager.domain.DiseaseDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 疾病百科
 * @author fei
 * @email 1992lcg@163.com
 * @date 2020-03-28 11:19:48
 */
@Mapper
public interface DiseaseDao {

	DiseaseDO get(Integer id);
	
	List<DiseaseDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DiseaseDO disease);
	
	int update(DiseaseDO disease);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
