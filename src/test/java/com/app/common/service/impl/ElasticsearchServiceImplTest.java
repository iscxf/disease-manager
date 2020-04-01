package com.app.common.service.impl;

import com.app.common.service.ElasticsearchService;
import com.app.common.utils.R;
import com.app.manager.domain.DiseaseDO;
import com.app.manager.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchServiceImplTest {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Test
    public void search() {
        Map<String, String> param = new HashMap<>(16);
        param.put("desc", "程序");
        R result = elasticsearchService.search("accounts", "person", param, 3, Person.class);
        System.out.println("ffffffffff"+ result);
    }

    @Test
    public void insertDocs(){
        DiseaseDO diseaseDO = new DiseaseDO();
        Person person = new Person();
        person.setUser("你发");
        person.setTitle("时高时低");
        person.setDesc("房东缝纫机发平发几个聘请");
        R result = elasticsearchService.insertDocs("accounts", "person", person);
        System.out.println("ffffffffff"+ result);
    }
}