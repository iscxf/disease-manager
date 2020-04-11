package com.app.manager.dao;

import com.app.manager.domain.PersonalHealthDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 训练库
 * @author admin
 * @email 1992lcg@163.com
 * @date 2020-03-24 22:01:56
 */
@Mapper
public interface TrainDataDao {

	PersonalHealthDo get(Integer id);
	
	List<PersonalHealthDo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PersonalHealthDo health);

	int update(PersonalHealthDo health);

	List<PersonalHealthDo> fetchData(Map<String, Object> map);
}
