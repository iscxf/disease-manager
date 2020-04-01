package com.app.manager.service.impl;

import com.app.manager.service.DiseaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import static org.junit.Assert.*;

@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest
public class DiseaseServiceImplTest {

    @Autowired
    private DiseaseService diseaseService;

    @Test
    public void get() {
    }

    @Test
    public void list() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }

    @Test
    public void readAndSave(){
        diseaseService.readAndSave("D:/GitRepository/java-programming-crawler-master/database.txt");
    }

    @Test
    public void insertDiseaseDocs(){
        diseaseService.insertDiseaseDocs();
    }

}