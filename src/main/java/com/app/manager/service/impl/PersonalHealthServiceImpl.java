package com.app.manager.service.impl;

import com.app.common.utils.R;
import com.app.common.utils.StringUtils;
import com.app.manager.dao.TrainDao;
import com.app.manager.dao.TrainDataDao;
import com.app.manager.domain.TrainDO;
import com.app.manager.domain.model.PredictParam;
import com.app.manager.domain.vo.PatientInfoVo;
import com.app.manager.service.DiseaseService;
import com.app.manager.utils.BayesUtil;
import com.app.manager.utils.DateUtil;
import com.app.manager.utils.RandomUtil;
import com.google.common.collect.Lists;
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
import org.springframework.util.CollectionUtils;

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
	private TrainDataDao trainDataDao;
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
		for (int i = 0; i < 500; i++) {
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
			String[] identityInfo = RandomUtil.getIdentityAndBirth("4806", "1960-1-1", "2010-12-31").split(",");
			//出生日期
			String birthString = identityInfo[0];
			Date birth = null;
			Date maxDate = null;
			try {
				birth = simpleDateFormat.parse(birthString);
				maxDate = simpleDateFormat.parse("1990-12-31");
			} catch(ParseException px) {
				//
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
			Double height = RandomUtil.getDoubleNum(140, 190, 2);
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
				if (RandomUtil.getIntNum(10) % 10 == 0){
					maritalStatus = "未婚";
				}else {
					maritalStatus = "已婚";
				}
			}
			//收缩压 90mmHg<收缩压<140mmHg、60mmHg<舒张压<90mmHg
			Double systolicPressure = RandomUtil.getDistributed(50, 120.0, 0);
			//舒张压
			Double diastolicPressure = RandomUtil.getDistributed(30, 75.0, 0);
			//空腹血糖 3.89～6.1mmol/l
			Double fastingBloodGlucose = RandomUtil.getDistributed(4, 5.5, 2);
			//餐后血糖
			Double postprandialBloodGlucose = RandomUtil.getDistributed(5, 9.5, 2);
			//	血脂总胆固醇 3.5~5.69
			Double totalCholesterol = RandomUtil.getDistributed(3, 4.9, 2);
			//	甘油三酯
			Double triglyceride = RandomUtil.getDistributed(2, 2.1, 2);
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

	/**
	 * 生成随机训练库信息
	 *
	 * @return
	 */
	@Override
	public R randomCreateTrainData() {
		for (int i = 0; i < 600; i++) {
			String sex = "";
			if (RandomUtil.getIntNum(6) % 2 == 0) {
				sex = "男";
			} else {
				sex = "女";
			}
			Date birth = RandomUtil.randomDate("1970-01-01", "2010-12-31");
			//身高
			Double height = RandomUtil.getDoubleNum(140, 190, 2);
			//体重
			Double weight = RandomUtil.getDoubleNum(40, 100, 2);
			//收缩压 90mmHg<收缩压<140mmHg、60mmHg<舒张压<90mmHg
			Double systolicPressure = RandomUtil.getDistributed(50, 120.0, 0);
			//舒张压
			Double diastolicPressure = RandomUtil.getDistributed(30, 75.0, 0);
			//空腹血糖 3.89～6.1mmol/l
			Double fastingBloodGlucose = RandomUtil.getDistributed(4, 5.5, 2);
			//餐后血糖
			Double postprandialBloodGlucose = RandomUtil.getDistributed(5, 9.5, 2);
			//	血脂总胆固醇 3.5~5.69
			Double totalCholesterol = RandomUtil.getDistributed(3, 4.9, 2);
			//	甘油三酯
			Double triglyceride = RandomUtil.getDistributed(2, 2.1, 2);
			//是否家族遗传
			int familialInheritance;
			if (RandomUtil.getIntNum(3) % 2 == 0) {
				familialInheritance = 1;
			}else {
				familialInheritance = 0;
			}
			//过厚皮脂
			Integer thickSebum = RandomUtil.getIntNum(2);
			if (2 == thickSebum) {
				thickSebum = 0;
			}

			Integer whetherDiabetes = RandomUtil.getIntNum(2);
			if (2 == whetherDiabetes) {
				whetherDiabetes = 0;
			}
			//是否糖尿病
			if (fastingBloodGlucose > 8 && postprandialBloodGlucose > 12) {
				whetherDiabetes = 1;
			}
			if (fastingBloodGlucose < 5 && postprandialBloodGlucose < 9) {
				whetherDiabetes = 0;
			}
			double bmi = (weight / (height * height));
			if (bmi > 25) {
				if (RandomUtil.getIntNum(3) % 2 == 0) {
					whetherDiabetes = 0;
				} else {
					whetherDiabetes = 1;
				}
			}

			PersonalHealthDo personalHealthDo = new PersonalHealthDo(sex, birth, height, weight, systolicPressure, diastolicPressure, fastingBloodGlucose,
					postprandialBloodGlucose, totalCholesterol, triglyceride, String.valueOf(whetherDiabetes), String.valueOf(familialInheritance), String.valueOf(thickSebum));
			trainDataDao.save(personalHealthDo);
		}
		return R.ok();
	}

	@Override
	public R forecastData(PredictParam data) {
		PersonalHealthDo personalHealthDo = new PersonalHealthDo();
		BeanUtils.copyProperties(data, personalHealthDo);
		List<String> dataList = changeDataClass(personalHealthDo);
		dataList.remove(dataList.size() - 1);
		log.info("trace forecastData dataList:[{}]",dataList);
		R searchSymptomResult = diseaseService.searchSymptom(data.getSymptom());
		log.info("trace searchSymptomResult:[{}]",searchSymptomResult);
		List<List<String>> fetchTrainDataList = fetchTrainData(null, dataList);
        Map<String, Object> calculationResult = BayesUtil.calculationProbability(fetchTrainDataList, dataList);
        log.info("trace calculationProbability calculationResult:[{}]",calculationResult);
		calculationResult.put("symptomResult", searchSymptomResult.get("data"));
//		calculationResult.put("symptomResult", null);
		return R.ok(calculationResult);
	}

	/**
	 * 抓取训练数据
	 *
	 * @return
	 */
	@Override
	public List<List<String>> fetchTrainData(Map<String, Object> param, List<String> testList) {
		List<List<String>> fetchTrainDataList = new ArrayList<>();
		List<PersonalHealthDo> fetchResult = trainDataDao.fetchData(param);
		//空腹血糖
		String fastingBloodGlucose = testList.get(7);
		//餐后血糖
		String postprandialBloodGlucose = testList.get(8);
		log.info("trace fastingBloodGlucose:[{}],postprandialBloodGlucose:[{}]",fastingBloodGlucose,postprandialBloodGlucose);
		for (PersonalHealthDo data : fetchResult){
			List<String> dataList = changeDataClass(data);
			if (testList.get(1).equals(dataList.get(1)) && testList.get(7).equals(dataList.get(7)) && testList.get(8).equals(dataList.get(8))){
//				log.info("trace fetchTrainData dataList:[{}]",dataList);
				fetchTrainDataList.add(dataList);
			}else if (testList.get(7).equals(dataList.get(7)) || testList.get(8).equals(dataList.get(8))){
				if (RandomUtil.getIntNum(5) % 5 == 0) {
					fetchTrainDataList.add(dataList);
				}
			}
		}

		log.info("trace fetchTrainDataList.size:[{}]",fetchTrainDataList.size());
		return fetchTrainDataList;
	}

	private List<String> changeDataClass(PersonalHealthDo data){
		List<String> dataList = new ArrayList<>();
		//性别
		if (StringUtils.isNotEmpty(data.getSex()) && "男".equals(data.getSex())){
			dataList.add("1");
		}else {
			dataList.add("2");
		}

		//年龄
		if (null == data.getBirth()){
			dataList.add("0");
		}else {
			Date birth = data.getBirth();
			if (birth.after(DateUtil.stringToDate("2000-01-01"))){
				dataList.add("1");
			}else if (birth.before(DateUtil.stringToDate("2000-01-01")) && birth.after(DateUtil.stringToDate("1990-01-01"))){
				dataList.add("2");
			}else if (birth.before(DateUtil.stringToDate("1990-01-01")) && birth.after(DateUtil.stringToDate("1980-01-01"))){
				dataList.add("3");
			}else if (birth.before(DateUtil.stringToDate("1980-01-01")) && birth.after(DateUtil.stringToDate("1970-01-01"))){
				dataList.add("4");
			}else if (birth.before(DateUtil.stringToDate("1970-01-01")) && birth.after(DateUtil.stringToDate("1960-01-01"))){
				dataList.add("5");
			}else if (birth.before(DateUtil.stringToDate("1960-01-01")) && birth.after(DateUtil.stringToDate("1950-01-01"))){
				dataList.add("6");
			}
		}

		//BMI
		if (null == data.getHeight() || null == data.getWeight()){
			dataList.add("0");
		}else {
			double height = data.getHeight() / 100;
			double bmi = (data.getWeight() / (height * height));
			if (bmi < 20) {
				dataList.add("1");
			} else if (bmi >= 20 && bmi <= 25) {
				dataList.add("2");
			} else if (bmi > 25 && bmi <= 30) {
				dataList.add("3");
			} else if (bmi > 30) {
				dataList.add("4");
			}
		}

		//舒张压
		if (null == data.getDiastolicPressure()){
			dataList.add("0");
		}else if (data.getDiastolicPressure() < 80 ){
			dataList.add("1");
		}else if (data.getDiastolicPressure() < 90 ){
			dataList.add("2");
		}else if (data.getDiastolicPressure() >= 90 && data.getDiastolicPressure() < 110){
			dataList.add("3");
		}else if (data.getDiastolicPressure() >= 110 && data.getDiastolicPressure() < 130){
			dataList.add("4");
		}else if (data.getDiastolicPressure() >= 130 ){
			dataList.add("5");
		}

		//收缩压
		if (null == data.getSystolicPressure()){
			dataList.add("0");
		}else if (data.getSystolicPressure() < 100 ){
			dataList.add("1");
		}else if (data.getSystolicPressure() < 110 ){
			dataList.add("2");
		}else if (data.getSystolicPressure() >= 110 && data.getSystolicPressure() < 130){
			dataList.add("3");
		}else if (data.getSystolicPressure() >= 130 && data.getSystolicPressure() < 150){
			dataList.add("4");
		}else if (data.getSystolicPressure() >= 150 ){
			dataList.add("5");
		}

		//血脂总胆固醇 > 5.2
		if (null == data.getTotalCholesterol()){
			dataList.add("0");
		}else if (data.getTotalCholesterol() < 4.4 ){
			dataList.add("1");
		}else if (data.getTotalCholesterol() < 4.8 ){
			dataList.add("2");
		}else if (data.getTotalCholesterol() >= 4.8 && data.getTotalCholesterol() < 5.4){
			dataList.add("3");
		}else if (data.getTotalCholesterol() >= 5.4 && data.getTotalCholesterol() < 7){
			dataList.add("4");
		}else if (data.getTotalCholesterol() >= 7 ){
			dataList.add("5");
		}

		//甘油三酯 > 1.7   高血脂
		if (null == data.getTriglyceride()){
			dataList.add("0");
		}else if (data.getTriglyceride() < 1.4 ){
			dataList.add("1");
		}else if (data.getTriglyceride() < 1.6 ){
			dataList.add("2");
		}else if (data.getTriglyceride() >= 1.6 && data.getTriglyceride() < 1.8){
			dataList.add("3");
		}else if (data.getTriglyceride() >= 1.8 && data.getTriglyceride() < 3){
			dataList.add("4");
		}else if (data.getTriglyceride() >= 3 ){
			dataList.add("5");
		}

		//空腹血糖 > 5.7
		if (null == data.getFastingBloodGlucose()){
			dataList.add("0");
		}else if (data.getFastingBloodGlucose() < 5.0 ){
			dataList.add("1");
		}else if (data.getFastingBloodGlucose() < 5.4 ){
			dataList.add("2");
		}else if (data.getFastingBloodGlucose() >= 5.4 && data.getFastingBloodGlucose() < 5.8){
			dataList.add("3");
		}else if (data.getFastingBloodGlucose() >= 5.8 && data.getFastingBloodGlucose() < 8){
			dataList.add("4");
		}else if (data.getFastingBloodGlucose() >= 8 ){
			dataList.add("5");
		}


		//餐后血糖 > 7   糖尿病
		if (null == data.getPostprandialBloodGlucose()){
			dataList.add("0");
		}else if (data.getPostprandialBloodGlucose() < 6 ){
			dataList.add("1");
		}else if (data.getPostprandialBloodGlucose() < 6.8 ){
			dataList.add("2");
		}else if (data.getPostprandialBloodGlucose() >= 6.8 && data.getPostprandialBloodGlucose() < 7.2){
			dataList.add("3");
		}else if (data.getPostprandialBloodGlucose() >= 7.2 && data.getPostprandialBloodGlucose() < 10){
			dataList.add("4");
		}else if (data.getPostprandialBloodGlucose() >= 10 ){
			dataList.add("5");
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
		//是否糖尿病  0 否    1 是
		if (StringUtils.isNotEmpty(data.getWhetherDiabetes()) && "1".equals(data.getWhetherDiabetes())){
			dataList.add("yes");
		}else {
			dataList.add("no");
		}
		return dataList;
	}
}
