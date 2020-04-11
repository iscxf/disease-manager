package com.app.manager.service.impl;

import com.app.common.utils.R;
import com.app.manager.domain.model.PredictParam;
import com.app.manager.service.PersonalHealthService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author chenxf
 * Created on 2020/3/25
 */
@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalHealthServiceImplTest {

    @Autowired
    private PersonalHealthService personalHealthService;

    @Test
    public void get() {
    }

    @Test
    public void list() {
    }

    @Test
    public void count() {
    }

    @Test
    public void save() {
        personalHealthService.save(null);
    }

    @Test
    public void update() {

    }

    @Test
    public void randomCreateInfo(){
        R result = personalHealthService.randomCreateInfo();
        System.out.println("ddddddd:"+result);
    }

    @Test
    public void randomCreateTrainData(){
        R result = personalHealthService.randomCreateTrainData();
        System.out.println("ddddddd:"+result);
    }

    @Test
    public void forecastData() {
        List<String> fetchList = Lists.newArrayList("man","yes", "yes", "yes","yes");
        System.out.println(fetchList);
        PredictParam predictParam = new PredictParam();
        predictParam.setSex("man");

        R result = personalHealthService.forecastData(new PredictParam());
        System.out.println("ddddddd:"+result);
    }

    @Test
    public void fetchTrainData() {
        List<List<String>> result = personalHealthService.fetchTrainData(null, new ArrayList<>());
        System.out.println(result);
    }
}