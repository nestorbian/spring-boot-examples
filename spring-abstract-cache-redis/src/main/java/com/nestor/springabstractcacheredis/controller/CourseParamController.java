package com.nestor.springabstractcacheredis.controller;

import com.nestor.springabstractcacheredis.po.CourseParam;
import com.nestor.springabstractcacheredis.repository.CourseParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.springabstractcacheredis.po.GradeParam;
import com.nestor.springabstractcacheredis.repository.GradeParamRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 课程controller
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@RestController
@Slf4j
public class CourseParamController {

	@Autowired
	private CourseParamRepository courseParamRepository;

	@GetMapping(path = "/course-params/{id}")
	public CourseParam getById(@ApiParam(name = "id") @PathVariable Long id) {
		return courseParamRepository.getById(id);
	}

	@PostMapping(path = "/course-params")
	public CourseParam updateById(@RequestBody CourseParam courseParam) {
		return courseParamRepository.updateById(courseParam);
	}

	@DeleteMapping(path = "/course-params")
	public Integer deleteById(@ApiParam(name = "id") @RequestParam Long id) {
		return courseParamRepository.deleteById(id);
	}

	@PostMapping(path = "/course-params/all")
	public Integer updateMany(@RequestBody CourseParam courseParam) {
		return courseParamRepository.updateMany(courseParam);
	}
}
