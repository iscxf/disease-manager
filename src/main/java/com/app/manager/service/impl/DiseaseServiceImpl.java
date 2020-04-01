package com.app.manager.service.impl;

import com.app.common.service.ElasticsearchService;
import com.app.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.manager.dao.DiseaseDao;
import com.app.manager.domain.DiseaseDO;
import com.app.manager.service.DiseaseService;


@Slf4j
@Service
public class DiseaseServiceImpl implements DiseaseService {

	@Autowired
	private ElasticsearchService elasticsearchService;
	@Autowired
	private DiseaseDao diseaseDao;
	
	@Override
	public DiseaseDO get(Integer id){
		return diseaseDao.get(id);
	}
	
	@Override
	public List<DiseaseDO> list(Map<String, Object> map){
		return diseaseDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return diseaseDao.count(map);
	}
	
	@Override
	public int save(DiseaseDO disease){
		return diseaseDao.save(disease);
	}
	
	@Override
	public int update(DiseaseDO disease){
		return diseaseDao.update(disease);
	}
	
	@Override
	public int remove(Integer id){
		return diseaseDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return diseaseDao.batchRemove(ids);
	}

	@Override
	public int readAndSave(String filePath) {
		try {
			FileReader fr=new FileReader(filePath);
			BufferedReader br=new BufferedReader(fr);
			String line="";
			int k = 0;
			List<String> buf = new ArrayList<>();
			Map<Integer, String> bufMap = new HashMap<>(16);
			while ((line=br.readLine())!=null) {
				if (line.contains("------2018")){
					if (!bufMap.isEmpty()) {
						DiseaseDO disease = new DiseaseDO(bufMap);
						System.out.println("trace bufMap:" + bufMap);
						save(disease);
					}
					k = 0;
					bufMap.clear();
					continue;
				}
				if (line.contains("-----")){
					k++;
				}else {
					bufMap.put(k, StringUtils.isEmpty(bufMap.get(k)) ? line : bufMap.get(k) + line);
				}
			}
			br.close();
			fr.close();
		}catch (Exception e){
			log.error("trace readAndSave error e:",e);
		}
		return 0;
	}

	@Override
	public R insertDiseaseDocs() {
		List<DiseaseDO> listDisease = list(null);
		for (DiseaseDO d : listDisease) {
			elasticsearchService.insertDocs("disease_bank", "disease", d);
		}
		return R.ok();
	}

    @Override
    public R searchSymptom(String symptom) {
        Map<String, String> param = new HashMap<>(16);
        param.put("symptom", symptom);
        return elasticsearchService.search("disease_bank", "disease", param, 3, DiseaseDO.class);
    }
}
