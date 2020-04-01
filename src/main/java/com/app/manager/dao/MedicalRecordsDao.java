package com.app.manager.dao;

import com.app.manager.domain.MedicalRecordsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 会诊记录
 * @author fei
 * @email 1992lcg@163.com
 * @date 2020-03-29 10:28:05
 */
@Mapper
public interface MedicalRecordsDao {

    MedicalRecordsDO get(Integer id);
	
	List<MedicalRecordsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(MedicalRecordsDO records);
	
	int update(MedicalRecordsDO records);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
