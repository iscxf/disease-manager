package com.app.manager.controller;

import java.util.List;
import java.util.Map;

import com.app.manager.service.DiseaseService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.manager.domain.PersonalHealthDo;
import com.app.manager.service.PersonalHealthService;
import com.app.common.utils.PageUtils;
import com.app.common.utils.Query;
import com.app.common.utils.R;

/**
 * 个人健康档案详情
 * 
 * @author admin
 * @email 1992lcg@163.com
 * @date 2020-03-24 22:01:56
 */
 
@Controller
@RequestMapping("/manager/health")
public class PersonalHealthController {
	@Autowired
	private PersonalHealthService personalHealthService;
	@Autowired
	private DiseaseService diseaseService;

	@GetMapping()
	@RequiresPermissions("manager:health:health")
	String Health(){
	    return "manager/health/health";
	}

	@GetMapping("/searchPersonal")
	@RequiresPermissions("manager:health:health")
	String searchPersonal(){
		return "manager/health/searchHealth";
	}

	@GetMapping("/insertDiseaseDocs")
	R insertDiseaseDocs(){
		return diseaseService.insertDiseaseDocs();
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("manager:health:health")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<PersonalHealthDo> healthList = personalHealthService.list(query);
		int total = personalHealthService.count(query);
		PageUtils pageUtils = new PageUtils(healthList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("manager:health:add")
	String add(){
	    return "manager/health/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("manager:health:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PersonalHealthDo health = personalHealthService.get(id);
		model.addAttribute("health", health);
	    return "manager/health/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("manager:health:add")
	public R save( PersonalHealthDo health){
		if(personalHealthService.save(health)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("manager:health:edit")
	public R update( PersonalHealthDo health){
		personalHealthService.update(health);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("manager:health:remove")
	public R remove( Integer id){
		if(personalHealthService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("manager:health:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		personalHealthService.batchRemove(ids);
		return R.ok();
	}
	
}
