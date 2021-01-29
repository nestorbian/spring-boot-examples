package com.nestor.springabstractcacheredis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nestor.springabstractcacheredis.po.GradeParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 年级controller
 *
 * @author : Nestor.Bian
 * @version : V 1.0
 * @date : 2020/3/22
 */
@RestController
@Api("根据名称以及等级获取年级参数")
@Slf4j
public class GradeParamController {

	// @Autowired
	// private GradeParamService gradeParamService;

	@ApiOperation(value = "根据名称以及等级获取年级参数")
	@GetMapping(path = "/grade-params/{name}/{level}")
	public GradeParam selectByNameAndLevel(@ApiParam(name = "name") @PathVariable String name,
			@ApiParam(name = "level") @PathVariable Integer level) {
		return new GradeParam();
	}

	// @GetMapping(path = "/grade-params")
	// public List<GradeParam> listWithFetchSize() {
	// return gradeParamService.listWithFetchSize();
	// }
	//
	// @PostMapping(path = "/grade-param")
	// public GradeParam insertOne(@RequestBody GradeParam gradeParam) {
	// gradeParamService.insertOne(gradeParam);
	// return gradeParam;
	// }

}
