package com.app.manager.service.impl;

import com.app.manager.domain.PersonalHealthDo;
import com.app.manager.service.PersonalHealthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.app.manager.dao.MedicalRecordsDao;
import com.app.manager.domain.MedicalRecordsDO;
import com.app.manager.service.MedicalRecordsService;



@Service
public class MedicalRecordsServiceImpl implements MedicalRecordsService {
	@Autowired
	private MedicalRecordsDao recordsDao;
	@Autowired
	private PersonalHealthService personalHealthService;
	
	@Override
	public MedicalRecordsDO get(Integer id){
		return recordsDao.get(id);
	}
	
	@Override
	public List<MedicalRecordsDO> list(Map<String, Object> map){
		return recordsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return recordsDao.count(map);
	}
	
	@Override
	public int save(MedicalRecordsDO records){
		if (StringUtils.isEmpty(records.getIdentity())){
			return 0;
		}
		PersonalHealthDo health = new PersonalHealthDo();
		BeanUtils.copyProperties(records, health);
		personalHealthService.updateByIdentity(health);
		return recordsDao.save(records);
	}
	
	@Override
	public int update(MedicalRecordsDO records){
		return recordsDao.update(records);
	}
	
	@Override
	public int remove(Integer id){
		return recordsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return recordsDao.batchRemove(ids);
	}
	
}
