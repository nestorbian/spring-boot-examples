package com.nestor.mybatisdemo.controller;

import com.nestor.mybatisdemo.enums.GradeLevel;
import com.nestor.mybatisdemo.po.GradeParam;
import com.nestor.mybatisdemo.service.GradeParamService;
import com.nestor.mybatisdemo.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 年级controller
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@RestController
@Slf4j
public class GradeParamController {

    @Autowired
    private GradeParamService gradeParamService;


    @GetMapping(path = "/grade-params/{name}/{level}")
    public GradeParam selectByNameAndLevel(@PathVariable String name, @PathVariable Integer level) {
        return gradeParamService.selectByNameAndLevel(name, EnumUtil.getByCode(level, GradeLevel.class));
    }

    @GetMapping(path = "/grade-params")
    public List<GradeParam> listWithFetchSize() {
        return gradeParamService.listWithFetchSize();
    }

    @PostMapping(path = "/grade-param")
    public GradeParam insertOne(@RequestBody GradeParam gradeParam) {
        gradeParamService.insertOne(gradeParam);
        return gradeParam;
    }

}
