package com.app.manager.service;

import com.app.common.utils.R;
import com.app.manager.domain.PersonalHealthDo;
import com.app.manager.domain.model.PredictParam;
import com.app.manager.domain.vo.PatientInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 个人健康档案详情
 * 
 * @author admin
 * @email 1992lcg@163.com
 * @date 2020-03-24 22:01:56
 */
public interface PersonalHealthService {
	
	PersonalHealthDo get(Integer id);

	List<PatientInfoVo> getPatientInfo(String identity);
	
	List<PersonalHealthDo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PersonalHealthDo health);
	
	int update(PersonalHealthDo health);

	int updateByIdentity(PersonalHealthDo health);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 生成随机信息
	 * @return
	 */
	R randomCreateInfo();

	R forecastData(PredictParam param);

	/**
	 * 抓取训练数据
	 * @return
	 */
	List<List<String>> fetchTrainData(Map<String, Object> param);


}
