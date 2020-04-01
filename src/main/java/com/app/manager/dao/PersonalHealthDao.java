package com.app.manager.dao;

import com.app.manager.domain.PersonalHealthDo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 个人健康档案详情
 * @author admin
 * @email 1992lcg@163.com
 * @date 2020-03-24 22:01:56
 */
@Mapper
public interface PersonalHealthDao {

	PersonalHealthDo get(Integer id);

	List<PersonalHealthDo> getPatientInfo(String identity);
	
	List<PersonalHealthDo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PersonalHealthDo health);
	
	int update(PersonalHealthDo health);

	int updateByIdentity(PersonalHealthDo health);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<PersonalHealthDo> fetchData(Map<String, Object> map);
}
