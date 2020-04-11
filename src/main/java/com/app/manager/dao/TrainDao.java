package com.app.manager.dao;

import com.app.manager.domain.TrainDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
* @author fei
 * @date 2020-03-30 21:46:03
 */
@Mapper
public interface TrainDao {

	TrainDO get(Integer id);
	
	List<TrainDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TrainDO train);
	
	int update(TrainDO train);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
