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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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

    @GetMapping(path = "/grade-params/like")
    public List<GradeParam> selectByNameLike(@RequestParam String name) {
        log.debug("name:{}", name);
        // System.err.println(gradeParam);
        return gradeParamService.selectByNameLike(name);
        // return Arrays.asList(gradeParam);
    }

    /**
     * FetchSize需要与流水查询一起用才起作用
     * 第二种流式查询：需要设置 fetchSize="-2147483648"
     * 不需要在url上加useCursorFetch=true
     * 如何判断是否生效：ResultSetImpl中的rowData的类型是否为ResultsetRowsStreaming
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.po.GradeParam>
     * @date : 2023/3/29 21:56
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping(path = "/grade-params")
    public List<GradeParam> listWithFetchSize() {
        return gradeParamService.listWithFetchSize();
    }

    @PostMapping(path = "/grade-param")
    public GradeParam insertOne(@RequestBody GradeParam gradeParam) {
        System.err.println(gradeParam);
        gradeParamService.insertOne(gradeParam);
        return gradeParam;
    }


    /**
     * 流式查询，需要在url上加useCursorFetch=true才能起作用
     * 如何判断是否生效：ResultSetImpl中的rowData的类型是否为ResultsetRowsCursor
     *
     * @param
     * @return java.util.List<com.nestor.mybatisdemo.po.GradeParam>
     * @date : 2021/1/14 21:28
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping(path = "/grade-params/streaming")
    public List<GradeParam> listWithStreaming() {
        return gradeParamService.listWithStreaming();
    }
}
