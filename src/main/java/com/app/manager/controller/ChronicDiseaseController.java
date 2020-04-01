package com.app.manager.controller;

import com.app.common.utils.PageUtils;
import com.app.common.utils.Query;
import com.app.common.utils.R;
import com.app.manager.domain.PersonalHealthDo;
import com.app.manager.domain.model.PredictParam;
import com.app.manager.service.PersonalHealthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxf
 * Created on 2020/3/25
 */
@Slf4j
@Controller
@RequestMapping("/manager/chronic")
public class ChronicDiseaseController {

    @Autowired
    private PersonalHealthService personalHealthService;

    /**
     * 糖尿病
     * @return
     */
    @GetMapping("/diabetes")
    @RequiresPermissions("manager:health:health")
    String diabetes(){
        return "manager/chronic/diabetes";
    }

    /**
     * 高血压
     * @return
     */
    @GetMapping("/hypertension")
    @RequiresPermissions("manager:health:health")
    String hypertension(){
        return "manager/chronic/hypertension";
    }


    /**
     * 算法预测
     * @return
     */
    @GetMapping("/algorithmicPrediction")
    @RequiresPermissions("manager:health:health")
    String algorithmicPrediction(){
        return "manager/chronic/algorithmicPrediction";
    }

    @ResponseBody
    @GetMapping("/hypertension/list")
    @RequiresPermissions("manager:health:health")
    public PageUtils hypertensionList(@RequestParam Map<String, Object> params){
        params.put("systolicPressure", 90);
        params.put("diastolicPressure", 120);
        //查询列表数据
        Query query = new Query(params);
        List<PersonalHealthDo> healthList = personalHealthService.list(query);
        int total = personalHealthService.count(query);
        PageUtils pageUtils = new PageUtils(healthList, total);
        return pageUtils;
    }

    @ResponseBody
    @GetMapping("/diabetes/list")
    @RequiresPermissions("manager:health:health")
    public PageUtils diabetesList(@RequestParam Map<String, Object> params){
        params.put("fastingBloodGlucose", 5.7);
        params.put("postprandialBloodGlucose", 7);
        //查询列表数据
        Query query = new Query(params);
        List<PersonalHealthDo> healthList = personalHealthService.list(query);
        int total = personalHealthService.count(query);
        PageUtils pageUtils = new PageUtils(healthList, total);
        return pageUtils;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/algorithmicPrediction/calculateData")
    @RequiresPermissions("manager:health:add")
    public R calculateData(PredictParam param){
        log.info("trace calculateData param:[{}]",param);
        return personalHealthService.forecastData(param);
    }

}
