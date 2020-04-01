package com.app.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.manager.domain.PersonalHealthDo;
import com.app.manager.domain.vo.PatientInfoVo;
import com.app.manager.service.PersonalHealthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.manager.domain.MedicalRecordsDO;
import com.app.manager.service.MedicalRecordsService;
import com.app.common.utils.PageUtils;
import com.app.common.utils.Query;
import com.app.common.utils.R;

/**
 * 会诊记录
 * 
 * @author fei
 * @email 1992lcg@163.com
 * @date 2020-03-29 10:28:05
 */

@Slf4j
@Controller
@RequestMapping("/manager/medicalRecords")
public class MedicalRecordsController {
	@Autowired
	private MedicalRecordsService recordsService;

	@Autowired
	private PersonalHealthService personalHealthService;
	
	@GetMapping()
	@RequiresPermissions("manager:medicalRecords:medicalRecords")
	String medicalRecords(){
	    return "manager/medicalRecords/medicalRecords";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("manager:medicalRecords:medicalRecords")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MedicalRecordsDO> recordsList = recordsService.list(query);
		int total = recordsService.count(query);
		PageUtils pageUtils = new PageUtils(recordsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{id}")
	@RequiresPermissions("manager:medicalRecords:edit")
	String addById(@PathVariable("id") Integer id,Model model){
		PersonalHealthDo personalHealthDo = personalHealthService.get(id);
		MedicalRecordsDO medicalRecords = new MedicalRecordsDO();
		BeanUtils.copyProperties(personalHealthDo, medicalRecords);
		model.addAttribute("medicalRecords", medicalRecords);
		return "manager/medicalRecords/addById";
	}

	@GetMapping("/add")
	@RequiresPermissions("manager:medicalRecords:edit")
	String add(){
		return "manager/medicalRecords/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("manager:medicalRecords:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		MedicalRecordsDO medicalRecords = recordsService.get(id);
		model.addAttribute("medicalRecords", medicalRecords);
	    return "manager/medicalRecords/edit";
	}


	@ResponseBody
	@RequestMapping(value = "/getPatientInfo")
	public R getPatientInfo(String identity){
		log.info("trace getPatientInfo identity:[{}]",identity);
		if (StringUtils.isEmpty(identity)){
			return R.error();
		}
		List<PatientInfoVo> result = personalHealthService.getPatientInfo(identity);
		Map<String, Object> map = new HashMap<>(16);
		map.put("data", result);
		return R.ok(map);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("manager:medicalRecords:edit")
	public R save( MedicalRecordsDO medicalRecords){
		if(recordsService.save(medicalRecords)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("manager:medicalRecords:edit")
	public R update( MedicalRecordsDO medicalRecords){
		recordsService.update(medicalRecords);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("manager:medicalRecords:remove")
	public R remove( Integer id){
		if(recordsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("manager:medicalRecords:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		recordsService.batchRemove(ids);
		return R.ok();
	}
	
}
