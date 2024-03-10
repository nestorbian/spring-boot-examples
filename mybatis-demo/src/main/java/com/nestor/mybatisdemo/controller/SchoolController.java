package com.nestor.mybatisdemo.controller;

import com.nestor.mybatisdemo.dto.SchoolDTO;
import com.nestor.mybatisdemo.po.School;
import com.nestor.mybatisdemo.service.SchoolService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 学校controller
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/4/7
 */
@RestController
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    /**
     * 验证<include/>的property value值里是否支持#{}
     * 结果：支持
     * 1.可转义，转义后不会被识别为占位符进行替换
     * 2.传入<sql/>中仅支持${}不支持#{}
     *
     *
     * @param name
     * @return java.util.List<com.nestor.mybatisdemo.po.School>
     * @date : 2020/4/9 19:57
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/schools")
    @SneakyThrows
    public List<School> listByInclude1(@RequestParam String name) {
        return schoolService.listByInclude1(name);
    }

    /**
     * 验证#{}能否传入到<sql/>中
     * 结果：能
     *
     * @param name
     * @return java.util.List<com.nestor.mybatisdemo.po.School>
     * @date : 2020/4/9 19:57
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/schools/{name}/{address}")
    public List<School> listByInclude2(@PathVariable String name, @PathVariable String address) {
        return schoolService.listByInclude2(name, address);
    }

    @PostMapping("/school")
    public String insertOne(School school) {
        return "SUCCESS";
    }

    /**
     * 集合关联的多结果集（ResultSet）
     *
     * @param id
     * @return
     * @date : 2020/4/17 16:22
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/school-with-more-resultSet/{id}")
    public List<SchoolDTO> selectSchoolWithMoreResultSet(@PathVariable Long id) {
        return schoolService.selectSchoolWithMoreResultSet(id);
    }

    /**
     * 多查询
     * 不支持
     *
     * @param id
     * @return
     * @date : 2020/4/17 16:22
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/school-with-more-select/{id}")
    public List<SchoolDTO> moreSelect(@PathVariable Long id) {
        return schoolService.moreSelect(id);
    }

    /**
     * 构造器方式返回结果集
     *
     * @param id
     * @return com.nestor.mybatisdemo.po.School
     * @date : 2020/4/22 11:18
     * @author : Nestor.Bian
     * @since : 1.0
     */
    @GetMapping("/school-with-constructor/{id}")
    public School selectByIdWithConstructor(@PathVariable Long id) {
        return schoolService.selectByIdWithConstructor(id);
    }

    @GetMapping("/schools/{id}")
    public School selectById(@PathVariable Long id) {
        return schoolService.selectById(id);
    }

}
