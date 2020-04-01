package com.app.manager.service.impl;

import com.app.common.utils.R;
import com.app.common.utils.StringUtils;
import com.app.manager.dao.TrainDao;
import com.app.manager.domain.TrainDO;
import com.app.manager.domain.model.PredictParam;
import com.app.manager.domain.vo.PatientInfoVo;
import com.app.manager.service.DiseaseService;
import com.app.manager.utils.BayesUtil;
import com.app.manager.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.app.manager.dao.PersonalHealthDao;
import com.app.manager.domain.PersonalHealthDo;
import com.app.manager.service.PersonalHealthService;

@Slf4j
@Service
public class PersonalHealthServiceImpl implements PersonalHealthService {

	@Autowired
	private DiseaseService diseaseService;
	@Autowired
	private PersonalHealthDao personalHealthDao;
	@Autowired
	private TrainDao trainDao;
	@Autowired
	RestHighLevelClient highLevelClient;
	
	@Override
	public PersonalHealthDo get(Integer id){
		return personalHealthDao.get(id);
	}

	@Override
	public List<PatientInfoVo> getPatientInfo(String identity) {
		List<PatientInfoVo> result = new ArrayList<>();
		Map<String, Object> param = new HashMap<>(16);
		param.put("identity", identity);
		List<PersonalHealthDo> personalList = personalHealthDao.list(param);
		for (PersonalHealthDo p : personalList){
			PatientInfoVo vo = new PatientInfoVo();
			BeanUtils.copyProperties(p, vo);
			result.add(vo);
		}
		return result;
	}

	@Override
	public List<PersonalHealthDo> list(Map<String, Object> map){
		return personalHealthDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return personalHealthDao.count(map);
	}
	
	@Override
	public int save(PersonalHealthDo health){
		double height = health.getHeight()/100;
		double bmi = (health.getWeight()/(height*height));
		health.setBmi(bmi);
		return personalHealthDao.save(health);
	}
	
	@Override
	public int update(PersonalHealthDo health){
		return personalHealthDao.update(health);
	}

	@Override
	public int updateByIdentity(PersonalHealthDo health) {
		return personalHealthDao.updateByIdentity(health);
	}

	@Override
	public int remove(Integer id){
		return personalHealthDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return personalHealthDao.batchRemove(ids);
	}

	/**
	 * 生成随机信息
	 *
	 * @return
	 */
	@Override
	public R randomCreateInfo() {
		for (int i = 0; i < 10; i++) {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String[] xmTmp = RandomUtil.getName().split(",");
			//姓名
			String name = xmTmp[1];
			//性别 1男、2女
			String sex = xmTmp[0];
			if ("1".equals(sex)){
				sex = "男";
			}else {
				sex = "女";
			}
			String[] identityInfo = RandomUtil.getIdentityAndBirth("4806", "1940-1-1", "2000-12-31").split(",");
			//出生日期
			String birthString = identityInfo[0];
			Date birth = null;
			Date maxDate = null;
			try {
				birth = simpleDateFormat.parse(birthString);
				maxDate = simpleDateFormat.parse("1990-12-31");
			} catch(ParseException px) {
				px.printStackTrace();
			}
			//身份证号
			String identity = identityInfo[1];
			//手机号
			String moblie = RandomUtil.getTel();
			//平均吸烟 (支/天)
			Integer averageSmoking = RandomUtil.getIntNum(6);
			if (RandomUtil.getIntNum(6)%2 == 0){
				averageSmoking = 0;
			}
			//身高
			Double height = RandomUtil.getDoubleNum(150, 189, 2);
			//体重
			Double weight = RandomUtil.getDoubleNum(40, 100, 2);
			//详细地址
			String address = RandomUtil.getRoad();
			//文化程度
			Integer educationalLevelInt = RandomUtil.getIntNum(4);
			String educationalLevel;
			switch (educationalLevelInt){
				case 1 : educationalLevel = "硕士或以上"; break;
				case 2 : educationalLevel = "本科"; break;
				case 3 : educationalLevel = "高中"; break;
				case 4 : educationalLevel = "高中以下"; break;
				default: educationalLevel = "未知"; break;
			}
			//婚姻状况
			Integer maritalStatusInt = RandomUtil.getIntNum(2);
			String maritalStatus;
			switch (maritalStatusInt){
				case 1 : maritalStatus = "已婚"; break;
				case 2 : maritalStatus = "未婚"; break;
				default: maritalStatus = "未知"; break;
			}
			if (birth.before(maxDate)){
				maritalStatus = "已婚";
			}
			//收缩压
			Double systolicPressure = RandomUtil.getDoubleNum(80,190, 2);
			//舒张压
			Double diastolicPressure = RandomUtil.getDoubleNum(50, 120, 2);
			//空腹血糖
			Double fastingBloodGlucose = RandomUtil.getDoubleNum(3, 10, 2);
			//餐后血糖
			Double postprandialBloodGlucose = RandomUtil.getDoubleNum(4, 13, 2);
			//	血脂总胆固醇
			Double totalCholesterol = RandomUtil.getDoubleNum(2, 8, 2);
			//	甘油三酯
			Double triglyceride = RandomUtil.getDoubleNum(1.2, 3.2, 2);
			//是否糖尿病
			Integer whetherDiabetes = RandomUtil.getIntNum(2);
			if (2 == whetherDiabetes){
				whetherDiabetes = 0;
			}
			//是否家族遗传
			Integer familialInheritance = RandomUtil.getIntNum(2);
			if (2 == familialInheritance){
				familialInheritance = 0;
			}
			//过厚皮脂
			Integer thickSebum = RandomUtil.getIntNum(2);
			if (2 == thickSebum){
				thickSebum = 0;
			}
			PersonalHealthDo personalHealthDo = new PersonalHealthDo(name, identity, sex, birth, averageSmoking, height, weight, address,
					moblie, educationalLevel, maritalStatus, systolicPressure, diastolicPressure, fastingBloodGlucose, postprandialBloodGlucose,
					totalCholesterol, triglyceride, whetherDiabetes.toString(), familialInheritance.toString(), thickSebum.toString());
			save(personalHealthDo);
		}
		return null;
	}

	@Override
	public R forecastData(PredictParam data) {
		List<String> dataList = new ArrayList<>();
		//性别 medical_records
		if (StringUtils.isNotEmpty(data.getSex()) && "男".equals(data.getSex())){
			dataList.add("man");
		}else {
			dataList.add("women");
		}
		//高血压
		if (null != data.getDiastolicPressure() && null != data.getSystolicPressure() &&
				(data.getDiastolicPressure() > 90 || data.getSystolicPressure() > 120)){
			dataList.add("yes");
		}else {
			dataList.add("no");
		}
		//血脂总胆固醇 > 5.2   甘油三酯 > 1.7   高血脂
		if (null != data.getTotalCholesterol() && null != data.getTriglyceride() &&
				(data.getTotalCholesterol() > 5.2 || data.getTriglyceride() > 1.7)){
			dataList.add("yes");
		}else {
			dataList.add("no");
		}
		//空腹血糖 > 5.7   餐后血糖 > 7   糖尿病
		if (null != data.getFastingBloodGlucose() && null != data.getPostprandialBloodGlucose() &&
				(data.getFastingBloodGlucose() > 5.7 || data.getPostprandialBloodGlucose() > 7)){
			dataList.add("yes");
		}else {
			dataList.add("no");
		}
		//是否过厚皮脂  0 否    1 是
		if (StringUtils.isNotEmpty(data.getThickSebum()) && "1".equals(data.getThickSebum())){
			dataList.add("yes");
		}else {
			dataList.add("no");
		}
		//是否家族遗传  0 否    1 是
		if (StringUtils.isNotEmpty(data.getFamilialInheritance()) && "1".equals(data.getFamilialInheritance())){
			dataList.add("yes");
		}else {
			dataList.add("no");
		}
		log.info("trace dataList:[{}]",dataList);
		R searchSymptomResult = diseaseService.searchSymptom(data.getSymptom());
		log.info("trace searchSymptomResult:[{}]",searchSymptomResult);
        Map<String, Object> calculationResult = BayesUtil.calculationProbability(fetchTrainData(null), dataList);
        log.info("trace calculationProbability calculationResult:[{}]",calculationResult);
		calculationResult.put("symptomResult", searchSymptomResult.get("data"));
		return R.ok(calculationResult);
	}

	/**
	 * 抓取训练数据
	 *
	 * @return
	 */
	@Override
	public List<List<String>> fetchTrainData(Map<String, Object> param) {
		List<List<String>> fetchTrainDataList = new ArrayList<>();
		List<TrainDO> trainList = trainDao.list(param);
		for (TrainDO data : trainList){
			List<String> dataList = new ArrayList<>();
			dataList.add(data.getSex());
			dataList.add(data.getHightPressure());
			dataList.add(data.getCholesterol());
			dataList.add(data.getThickSebum());
			dataList.add(data.getFamilialInheritance());
			dataList.add(data.getWhetherDiabetes());
			fetchTrainDataList.add(dataList);
		}
//		List<PersonalHealthDo> fetchResult = personalHealthDao.fetchData(param);
//		for (PersonalHealthDo data : fetchResult){
//			List<String> dataList = new ArrayList<>();
//			//性别
//			if (StringUtils.isNotEmpty(data.getSex()) && "男".equals(data.getSex())){
//				dataList.add("man");
//			}else {
//				dataList.add("women");
//			}
//			//高血压
//			if (null != data.getDiastolicPressure() && null != data.getSystolicPressure() &&
//					(data.getDiastolicPressure() > 90 || data.getSystolicPressure() > 120)){
//				dataList.add("yes");
//			}else {
//				dataList.add("no");
//			}
//			//血脂总胆固醇 > 5.2   甘油三酯 > 1.7   高血脂
//			if (null != data.getTotalCholesterol() && null != data.getTriglyceride() &&
//					(data.getTotalCholesterol() > 5.2 || data.getTriglyceride() > 1.7)){
//				dataList.add("yes");
//			}else {
//				dataList.add("no");
//			}
//			//空腹血糖 > 5.7   餐后血糖 > 7   糖尿病
////			if (null != data.getFastingBloodGlucose() && null != data.getPostprandialBloodGlucose() &&
////					(data.getFastingBloodGlucose() > 5.7 || data.getPostprandialBloodGlucose() > 7)){
////				dataList.add("yes");
////			}else {
////				dataList.add("no");
////			}
//			//是否过厚皮脂  0 否    1 是
//			if (StringUtils.isNotEmpty(data.getThickSebum()) && "1".equals(data.getThickSebum())){
//				dataList.add("yes");
//			}else {
//				dataList.add("no");
//			}
//			//是否家族遗传  0 否    1 是
//			if (StringUtils.isNotEmpty(data.getFamilialInheritance()) && "1".equals(data.getFamilialInheritance())){
//				dataList.add("yes");
//			}else {
//				dataList.add("no");
//			}
//			//是否糖尿病  0 否    1 是
//			if (StringUtils.isNotEmpty(data.getWhetherDiabetes()) && "1".equals(data.getWhetherDiabetes())){
//				dataList.add("yes");
//			}else {
//				dataList.add("no");
//			}
//			fetchTrainDataList.add(dataList);
//		}
		System.out.println(fetchTrainDataList);
		return fetchTrainDataList;
	}
}
